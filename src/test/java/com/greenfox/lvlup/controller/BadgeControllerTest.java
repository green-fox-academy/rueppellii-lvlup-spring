package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.dto.UserBadgeSetDTO;
import com.greenfox.lvlup.security.JwtAuthenticationEntryPoint;
import com.greenfox.lvlup.security.JwtAuthenticationProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.greenfox.lvlup.util.Converter.stringify;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BadgeController.class)
@EnableResourceServer
@AutoConfigureMockMvc(secure = false)
public class BadgeControllerTest {

  @Autowired
  private WebApplicationContext context;

  @Autowired
  public MockMvc mvc;

  @MockBean
  private JwtAuthenticationProvider authenticationProvider;

  @MockBean
  private JwtAuthenticationEntryPoint entryPoint;

  private UserBadgeSetDTO testBadgeSet = new UserBadgeSetDTO();
  private String tokenString = "testToken";

  @Before
  public void setup() {
    authenticationProvider = new JwtAuthenticationProvider();
    mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
  }

  @Test
  public void showBadgesTestWithCorrectHeader() throws Exception {
    mvc.perform(get("/api/badges")
        .header("userTokenAuth", tokenString)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(stringify(testBadgeSet)));
  }

  @Test
  public void showBadgesTestWithoutToken() throws Exception {
    mvc.perform(get("/api/badges")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnauthorized())
        .andExpect(jsonPath("$.error").value("Unauthorized"));
  }
}
