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
    BadgeDTO invalidBadgeDto = new BadgeDTO("2.3", "", "general");

    private String stringify(Object object) throws JsonProcessingException {
      return new ObjectMapper().writeValueAsString(object);
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addBadgeValidRequestReturns201Created() throws Exception {
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





