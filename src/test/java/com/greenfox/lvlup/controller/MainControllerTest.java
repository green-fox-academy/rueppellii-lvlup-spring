package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.controller.MainController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenfox.lvlup.model.Badge;
import com.greenfox.lvlup.model.BadgeDTO;
//import com.sun.xml.internal.messaging.saaj.packaging.mime.Header;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
public class MainControllerTest {
  String token = "token123";

  Badge validBadge = new Badge("English speaker",
      2,
      3,
      "Hello World! My English is bloody gorgeous.",
      new ArrayList<>(Arrays.asList("balazs.jozsef", "benedek.vamosi", "balazs.barna")));

  Badge inValidBadge = new Badge("English speaker",
      2,
      3,
      "Hello World! My English is bloody gorgeous.");

  BadgeDTO validBadgeDto = new BadgeDTO("2.3", "Badge inserter", "general");

  private String sucessfulString = "{ \"myPitches\": [ { \"timestamp\": \"2018-11-29 17:10:47\", \"username\": \"balazs.barna\", " +
      "\"badgeName\": \"Programming\", \"oldLevel\": 2, \"pitchedLevel\": 3, \"pitchMessage\": " +
      "\"I improved in React, Redux, basic JS, NodeJS, Express and in LowDB, pls give me more money\", " +
      "\"holders\": [ { \"name\": \"sandor.vass\", \"message\": null, \"pitchStatus\": false },... ], } ], " +
      "\"pitchesToReview\": [ { \"timestamp\": \"2018-11-29 17:10:47\", \"username\": \"berei.daniel\", " +
      "\"badgeName\": \"English speaker\", \"oldLevel\": 2, \"pitchedLevel\": 3, \"pitchMessage\": " +
      "\"I was working abroad for six years, so I can speak english very well. Pls improve my badge level to 3.\", " +
      "\"holders\": [ { \"name\": \"balazs.jozsef\", \"message\": \"Yes, you are able to speak english\", " +
      "\"pitchStatus\": true },... ] }";

  private String success = "{ \"badges\": [ { \"name\": \"Process improver\", \"level\": \"2\" }, { \"name\": " +
      "\"English speaker\", \"level\": \"1\" }, { \"name\": \"Feedback giver\", \"level\": \"1\" } ] }";
  private String tokenString = "6bb9q";


  @Autowired
  private MockMvc mockMvc;


/*  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(new MainController()).build();
  }
  @Before
  public void setup() {
    this.mvc = standaloneSetup(new MainController()).build();
  @Before
  public void setUp() throws Exception {
    this.mvc = standaloneSetup(new MainController()).build();
  }*/

//1. Test endpoint /badges:

  @Test
  public void showBadgesTestWithCorrectHeader() throws Exception {
    mockMvc.perform(get("/badges")
        .header("userTokenAuth", tokenString)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(success));
  }

  @Test
  public void showBadgesTestMediaTypeIsCorrect() throws Exception {
    mockMvc.perform(get("/badges")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
        .andExpect(status().isUnauthorized());
  }

  @Test
  public void showBadgesTestWithoutToken() throws Exception {
    mockMvc.perform(get("/badges")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnauthorized());
  }

  //2. Test endpoint Post /pitch
  @Test
  public void pitchBadgeValidHeaderAndBodyCheckStatus() throws Exception {
    this.mockMvc.perform(post("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", token)
        .content(stringify(validBadge)))
        .andExpect(status().isCreated())
        .andReturn();
  }

  @Test
  public void pitchBadgeValidHeaderAndBodyCheckMessage() throws Exception {
    this.mockMvc.perform(post("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", token)
        .content(stringify(validBadge)))
        .andExpect(jsonPath("$.message").value("Success"))
        .andReturn();
  }

  @Test
  public void pitchBadgeMissingContentTypeCheckStatus() throws Exception {
    this.mockMvc.perform(post("/pitch")
        .header("userTokenAuth", token)
        .content(stringify(validBadge)))
        .andExpect(status().isUnsupportedMediaType())
        .andReturn();
  }

  @Test
  public void pitchBadgeInvalidTokenCheckStatus() throws Exception {
    this.mockMvc.perform(post("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", "")
        .content(stringify(validBadge)))
        .andExpect(status().isUnauthorized())
        .andReturn();
  }

  @Test
  public void pitchBadgeInvalidTokenCheckErrorMessage() throws Exception {
    this.mockMvc.perform(post("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", "")
        .content(stringify(validBadge)))
        .andExpect(jsonPath("$.error").value("Unauthorized"))
        .andReturn();
  }

  @Test
  public void pitchBadgeInvalidRequestBody() throws Exception {
    this.mockMvc.perform(post("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", token)
        .content(stringify(inValidBadge)))
        .andExpect(status().isBadRequest())
        .andReturn();
  }


  private String stringify(Object object) throws JsonProcessingException {
    return new ObjectMapper().writeValueAsString(object);
  }

//3. Test Post /admin/add
  @Test
  public void addBadgeValidRequestReturns201Created() throws Exception {
    String validBadgeDtoInJson = String.format("{\"version\": \"2.3\",\"name\": \"Badge inserter\", \"tag\": \"general\", \"levels\": \"[]\"}");

    this.mockMvc.perform(post("/admin/add")
        .header("userTokenAuth", token)
        .contentType(MediaType.APPLICATION_JSON)
        .content(validBadgeDtoInJson))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(content().string("\"message\": \"Success\""));
  }

  @Test
  public void invalidNameErrorReturns404NotFound() throws Exception {
    String invalidBadgeDtoInJson = String.format("{\"version\": \"2.3\",\"name\": \"\", \"tag\": \"general\", \"levels\": \"[]\"}");


    this.mockMvc.perform(post("/admin/add")
        .header("userTokenAuth", token)
        .contentType(MediaType.APPLICATION_JSON)
        .content(invalidBadgeDtoInJson))
        .andDo(print())
        .andExpect(status().isNotFound())
        .andExpect(content().json("{\n" +
            "    \"errorMessage\": \"Please provide all fields: 1 error(s)\",\n" +
            "    \"errors\": [\n" +
            "        \"Badge name must not be blank!\"\n" +
            "    ]\n" +
            "}"));
  }

  @Test
  public void addBadgeNotContainingTokenReturns404NotFound() throws Exception {
    String validBadgeDtoInJson = String.format("{\"version\": \"2.3\",\"name\": \"Badge inserter\", \"tag\": \"general\", \"levels\": \"[]\"}");

    this.mockMvc.perform(post("/admin/add")
        .header("userTokenAuth", "")
        .contentType(MediaType.APPLICATION_JSON)
        .content(validBadgeDtoInJson))
        .andDo(print())
        .andExpect(status().isNotFound())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(content().string("\"error\": \"Please provide all fields\""));
  }

//4. Test /pitches

  @Test
  public void getPitchesWithCorrectHeader() throws Exception {
    mockMvc.perform(get("/pitches")
        .header("userTokenAuth", token)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(sucessfulString))
        .andReturn();
  }

  @Test
  public void getPitchesWithIncorrectMediaType() throws Exception {
    mockMvc.perform(get("/pitches")
        .contentType(MediaType.APPLICATION_ATOM_XML))
        .andExpect(status().isUnauthorized());
  }

  @Test
  public void checkIfTokenIsProvided() throws Exception {
    mockMvc.perform(get("/pitches")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnauthorized())
        .andReturn();
  }
}
