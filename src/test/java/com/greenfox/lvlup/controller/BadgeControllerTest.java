package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.mockdto.UserBadgeSetDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.greenfox.lvlup.util.Converter.stringify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BadgeController.class)
public class BadgeControllerTest {

  @Autowired
  public MockMvc mvc;

  private UserBadgeSetDTO testBadgeSet = new UserBadgeSetDTO();
  private String tokenString = "testToken";

  @Test
  public void showBadgesTestWithCorrectHeader() throws Exception {
    mvc.perform(get("/badges")
        .header("userTokenAuth", tokenString)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(stringify(testBadgeSet)));
  }

  @Test
  public void showBadgesTestWithoutToken() throws Exception {
    mvc.perform(get("/badges")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnauthorized())
        .andExpect(jsonPath("$.error").value("Unauthorized"));
  }
}
