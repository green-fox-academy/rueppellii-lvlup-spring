package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.BadgeDTO;
import com.sun.xml.internal.messaging.saaj.packaging.mime.Header;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
  private WebApplicationContext wac;
  private MockMvc mockMvc;

  String toTest = String.format("{\"version\": \"2.3\",\"name\": \"Badge inserter\", \"tag\": \"general\", \"levels\": \"[]\"}");
  //BadgeDTO toTest = new BadgeDTO("2.3", "Badge inserter", "general");
  String token = "hkalj253";

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  @Test
  public void addBadgeValidRequestReturns201Created() throws Exception {

    this.mockMvc.perform(post("/admin/add")
        .header("userTokenAuth", token)
        .contentType(MediaType.APPLICATION_JSON)
        .content(toTest))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(content().string("\"message\": \"Success\""));

  }


/*  @Test
  public void invalidNameError() throws Exception {
    String jsonTaskWithBlankName = String.format("{\"name\": \"\",\"description\": \"Description\"}");

    this.mockMvc.perform(post("task")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonTaskWithBlankName))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(content().json("{\"errors\":[\"Task name must not be blank!\"],\"errorMessage\":\"Validation failed. 1 error(s)\"}"));

  }*/
}
