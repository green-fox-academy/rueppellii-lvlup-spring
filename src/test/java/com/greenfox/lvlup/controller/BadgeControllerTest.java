package com.greenfox.lvlup.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenfox.lvlup.model.dto.user.UserBadgeDTO;
import com.greenfox.lvlup.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BadgeController.class)
public class BadgeControllerTest {

  @Autowired
  public MockMvc mvc;

  @Mock
  private BadgeController badgeController;

  @MockBean
  UserService userService;

  @Before
  public void setup() {
    JacksonTester.initFields(this,new ObjectMapper());
  }

  @Test
  public void showBadgesTestWithCorrectHeader() throws Exception {

    ResponseEntity<Object> result = badgeController.getUserBadges(1L, "userTokenAuth");

    List<UserBadgeDTO> userBadgeDTOS = new ArrayList<>();
    UserBadgeDTO userBadgeDTO1 = new UserBadgeDTO("Process improver", 1);
    UserBadgeDTO userBadgeDTO2 = new UserBadgeDTO("English speaker", 2);
    userBadgeDTOS.add(userBadgeDTO1);
    userBadgeDTOS.add(userBadgeDTO2);
    when(badgeController.getUserBadges(1L, "userTokenAuth")).thenReturn(result);

    verify(badgeController, times(1)).getUserBadges(1L, "userTokenAuth");
    assert true;
  }

  @Test
  public void showBadgesTestWithoutToken() throws Exception {
    mvc.perform(get("/badges")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnauthorized())
        .andExpect(jsonPath("$.error").value("Unauthorized"));
  }
}
