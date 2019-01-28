package com.greenfox.lvlup.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenfox.lvlup.model.MockingElements;
import com.greenfox.lvlup.model.PitchDTO;
//import com.sun.xml.internal.messaging.saaj.packaging.mime.Header;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(PitchController.class)
public class PitchControllerTest {

 MockingElements elements = new MockingElements();

  private String sucessfulString = "{ \"myPitches\": [ { \"timestamp\": \"2018-11-29 17:10:47\", \"username\": \"balazs.barna\", " +
      "\"badgeName\": \"Programming\", \"oldLevel\": 2, \"pitchedLevel\": 3, \"pitchMessage\": " +
      "\"I improved in React, Redux, basic JS, NodeJS, Express and in LowDB, pls give me more money\", " +
      "\"holders\": [ { \"name\": \"sandor.vass\", \"message\": null, \"pitchStatus\": false },... ], } ], " +
      "\"pitchesToReview\": [ { \"timestamp\": \"2018-11-29 17:10:47\", \"username\": \"berei.daniel\", " +
      "\"badgeName\": \"English speaker\", \"oldLevel\": 2, \"pitchedLevel\": 3, \"pitchMessage\": " +
      "\"I was working abroad for six years, so I can speak english very well. Pls improve my badge level to 3.\", " +
      "\"holders\": [ { \"name\": \"balazs.jozsef\", \"message\": \"Yes, you are able to speak english\", " +
      "\"pitchStatus\": true },... ] }";

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void pitchBadgeValidHeaderAndBodyCheckStatus() throws Exception {
    this.mockMvc.perform(post("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", elements.getValidToken())
        .content(stringify(elements.getValidPitchDTO())))
        .andExpect(status().isCreated())
        .andReturn();
  }

  @Test
  public void pitchBadgeValidHeaderAndBodyCheckMessage() throws Exception {
    this.mockMvc.perform(post("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", elements.getValidToken())
        .content(stringify(elements.getValidPitchDTO())))
        .andExpect(jsonPath("$.message").value("Success"))
        .andReturn();
  }

  @Test
  public void pitchBadgeMissingContentTypeCheckStatus() throws Exception {
    this.mockMvc.perform(post("/pitch")
        .header("userTokenAuth", elements.getValidToken())
        .content(stringify(elements.getValidPitchDTO())))
        .andExpect(status().isUnsupportedMediaType())
        .andReturn();
  }

  @Test
  public void pitchBadgeInvalidTokenCheckStatus() throws Exception {
    this.mockMvc.perform(post("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", "")
        .content(stringify(elements.getValidPitchDTO())))
        .andExpect(status().isUnauthorized())
        .andReturn();
  }

  @Test
  public void pitchBadgeInvalidTokenCheckErrorMessage() throws Exception {
    this.mockMvc.perform(post("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", "")
        .content(stringify(elements.getValidPitchDTO())))
        .andExpect(jsonPath("$.error").value("Unauthorized"))
        .andReturn();
  }

  @Test
  public void pitchBadgeInvalidRequestBody() throws Exception {
    this.mockMvc.perform(post("/pitch")
        .contentType(MediaType.APPLICATION_JSON)
        .header("userTokenAuth", elements.getValidToken())
        .content(stringify(elements.getInvalidPitchDTO())))
        .andExpect(status().isBadRequest())
        .andReturn();
  }


  private String stringify(Object object) throws JsonProcessingException {
    return new ObjectMapper().writeValueAsString(object);
  }



  @Test
  public void getPitchesWithCorrectHeader() throws Exception {
    mockMvc.perform(get("/pitches")
        .header("userTokenAuth", elements.getValidToken())
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
