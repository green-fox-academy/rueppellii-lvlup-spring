package com.greenfox.lvlup.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@WebMvcTest(MainController.class)
public class MainControllerTest {

  private MockMvc mvc;
  private String success = "{ \"badges\": [ { \"name\": \"Process improver\", \"level\": \"2\" }, { \"name\": " +
      "\"English speaker\", \"level\": \"1\" }, { \"name\": \"Feedback giver\", \"level\": \"1\" } ] }";
  private String tokenString = "6bb9q";

  @Before
  public void setup() {
    this.mvc = standaloneSetup(new MainController()).build();
  }

  //is everything ok? (should test the response json too)
  @Test
  public void showBadgesTestWithCorrectHeader() throws Exception {
    mvc.perform(get("/badges")
        .header("userTokenAuth", tokenString)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(success))
        .andReturn();
  }

    // check the mediatype
  @Test
  public void showBadgesTestMediaTypeIsCorrect() throws Exception {
    mvc.perform(get("/badges")
      .contentType(MediaType.APPLICATION_FORM_URLENCODED))
      .andExpect(status().isUnauthorized());
  }

 //check if the token is there
  @Test
  public void showBadgesTestWithoutToken() throws Exception {
    mvc.perform(get("/badges")
      .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnauthorized())
        .andReturn();
  }
}
