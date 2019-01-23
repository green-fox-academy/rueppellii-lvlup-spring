package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.Badge;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
public class MainControllerTest {

  Badge validBadge = new Badge("English speaker",
      2,
      3,
      "Hello World! My English is bloody gorgeous.",
      new ArrayList<String>(Arrays.asList("balazs.jozsef", "benedek.vamosi", "balazs.barna")));

  String badgeInJson = "{ \"badgeName\": \"" + "English speaker" + "\", " +
        "\"oldLVL\": \"" + 2 + "\"," +
        "\"pitchedLVL\": \"" + 3 + "\", " +
        "\"pitchMessage\": \"" + "Hello World! My English is bloody gorgeous." + "\", " +
        "\"holders\": [\"balazs.jozsef\", \"balazs.jozsef\", \"balazs.jozsef\"]}";


  String token = "token123";

  @Autowired
  private WebApplicationContext wac;

  private MockMvc mockMvc;

  @Before
  public void setup() throws Exception {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }


  @Test
  public void pitchBadge() throws Exception{
      this.mockMvc.perform(post("/pitch")
          .contentType(MediaType.APPLICATION_JSON)
          .header("userTokenAuth", token)
          .content(badgeInJson))
          .andExpect(status().isCreated())
          .andReturn();
  }
}
