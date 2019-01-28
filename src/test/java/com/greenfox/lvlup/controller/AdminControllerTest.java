package com.greenfox.lvlup.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AdminController.class)
public class AdminControllerTest {
  String token = "TestToken123";
  BadgeDTO validBadgeDto = new BadgeDTO("2.3", "Test badge", "general");
  BadgeDTO invalidBadgeDto = new BadgeDTO("2.3", "Test badge", "");

  private String stringify(Object object) throws JsonProcessingException {
    return new ObjectMapper().writeValueAsString(object);
  }

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void addBadgeValidRequestReturns201Created() throws Exception {
    //String validBadgeDtoInJson = String.format("{\"version\": \"2.3\",\"name\": \"Test badge\", \"tag\": \"general\", \"levels\": \"[]\"}");

    this.mockMvc.perform(post("/admin/add")
        .header("userTokenAuth", token)
        .contentType(MediaType.APPLICATION_JSON)
        .content(stringify(validBadgeDto)))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.message").value("Success"));
  }

  @Test
  public void invalidNameErrorReturns404NotFound() throws Exception {
   // String invalidBadgeDtoInJson = String.format("{\"version\": \"2.3\",\"name\": \"\", \"tag\": \"general\", \"levels\": \"[]\"}");


    this.mockMvc.perform(post("/admin/add")
        .header("userTokenAuth", token)
        .contentType(MediaType.APPLICATION_JSON)
        .content(stringify(invalidBadgeDto)))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(content().json("{\n" +
            "    \"errorMessage\": \"Please provide all fields: 1 error(s)\",\n" +
            "    \"errors\": [\n" +
            "        \"Badge name must not be blank!\"\n" +
            "    ]\n" +
            "}"));
  }

  @Test
  public void addBadgeNotContainingTokenReturns404NotFound() throws Exception {
    //String validBadgeDtoInJson = String.format("{\"version\": \"2.3\",\"name\": \"Badge inserter\", \"tag\": \"general\", \"levels\": \"[]\"}");

    this.mockMvc.perform(post("/admin/add")
        .header("userTokenAuth", "")
        .contentType(MediaType.APPLICATION_JSON)
        .content(stringify(validBadgeDto)))
        .andDo(print())
        .andExpect(status().isNotFound())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.error").value("Please provide all fields"));
  }
}
/*

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



 */
