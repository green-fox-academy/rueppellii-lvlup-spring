package com.greenfox.lvlup.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@WebMvcTest(BadgeController.class)
public class BadgeControllerTest {

  private MockMvc mvc;
  private UserBadgeSetDTO testBadgeSet = new UserBadgeSetDTO();
  private String tokenString = "testToken";

  @Before
  public void setup() {
    this.mvc = standaloneSetup(new BadgeController()).build();
  }

  @Test
  public void showBadgesTestWithCorrectHeader() throws Exception {
    mvc.perform(get("/badges")
        .header("userTokenAuth", tokenString)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(testBadgeSet.toString()));
  }

  @Test
  public void showBadgesTestMediaTypeNotCorrect() throws Exception {
    try {
      mvc.perform(get("/badges")
          .header("userTokenAuth", tokenString)
          .contentType(MediaType.APPLICATION_FORM_URLENCODED))
          .andExpect(status().isUnauthorized())
          .andDo(print());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void showBadgesTestWithoutToken() throws Exception {
    try {
      mvc.perform(get("/badges")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isUnauthorized());
    } catch (Exception e) {
      System.out.println(e.toString());
    }
  }
}
