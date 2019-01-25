package com.greenfox.lvlup.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenfox.lvlup.model.Badge;
import com.greenfox.lvlup.model.BadgeDTO;
import com.sun.xml.internal.messaging.saaj.packaging.mime.Header;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
public class MainControllerTest {

  Badge validBadge = new Badge("English speaker",
      2,
      3,
      "Hello World! My English is bloody gorgeous.",
      new ArrayList<>(Arrays.asList("balazs.jozsef", "benedek.vamosi", "balazs.barna")));

  Badge inValidBadge = new Badge("English speaker",
      2,
      3,
      "Hello World! My English is bloody gorgeous.");

  String token = "token123";

  private MockMvc mockMvc;

  BadgeDTO validBadgeDto = new BadgeDTO("2.3", "Badge inserter", "general");
 
  
  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(new MainController()).build();
  }

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
}
