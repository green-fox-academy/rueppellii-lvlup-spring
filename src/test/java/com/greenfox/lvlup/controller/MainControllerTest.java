package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.BadgeDTO;
import com.sun.xml.internal.messaging.saaj.packaging.mime.Header;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
public class MainControllerTest {

  @Autowired
  private MockMvc mockMvc;

  BadgeDTO validBadgeDto = new BadgeDTO("2.3", "Badge inserter", "general");
  String token = "hkalj253";

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
