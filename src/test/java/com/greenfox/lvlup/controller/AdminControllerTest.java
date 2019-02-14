package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.dto.BadgeDTO;
import com.greenfox.lvlup.security.JwtAuthenticationProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.greenfox.lvlup.util.Converter.stringify;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

  @RunWith(SpringRunner.class)
  @WebMvcTest(AdminController.class)
  public class AdminControllerTest {
    String token = "a valid token";
    BadgeDTO validBadgeDto = new BadgeDTO("2.3", "Test badge", "general");
    BadgeDTO invalidBadgeDto = new BadgeDTO("2.3", "", "general");

    private JwtAuthenticationProvider provider;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
      provider = new JwtAuthenticationProvider();
      mockMvc = MockMvcBuilders
          .webAppContextSetup(context)
          .apply(springSecurity())
          .build();
    }

    @Test
    public void addBadgeValidRequestReturns201Created() throws Exception {
      this.mockMvc.perform(post("/admin/add")
          .header("Authentication", "Bearer " + token)
          .contentType(MediaType.APPLICATION_JSON)
          .content(stringify(validBadgeDto)))
          .andDo(print())
          .andExpect(status().isCreated())
          .andExpect(content()
              .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$.message").value("Success"));
    }

    @Test
    public void invalidNameErrorReturns400BadRequest() throws Exception {
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
    public void addBadgeNotContainingTokenValueReturns401Unauthorized() throws Exception {
      this.mockMvc.perform(post("/admin/add")
          .header("userTokenAuth", "")
          .contentType(MediaType.APPLICATION_JSON)
          .content(stringify(validBadgeDto)))
          .andDo(print())
          .andExpect(status().isUnauthorized())
          .andExpect(content()
              .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$.error").value("Unauthorized"));
    }

    @Test
    public void addBadgeNotContainingTokenReturns401Unauthorized() throws Exception {
      this.mockMvc.perform(post("/admin/add")
              .contentType(MediaType.APPLICATION_JSON)
              .content(stringify(validBadgeDto)))
              .andDo(print())
              .andExpect(status().isUnauthorized())
              .andExpect(content()
                      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
              .andExpect(jsonPath("$.error").value("Unauthorized"));
    }

    @Test
    public void addBadgeNotContainingMediatypeReturns415UnsupportedMediaType() throws Exception {
      this.mockMvc.perform(post("/admin/add")
          .header("userTokenAuth", token)
          .content(stringify(validBadgeDto)))
          .andExpect(status().isUnsupportedMediaType());
    }
  }
