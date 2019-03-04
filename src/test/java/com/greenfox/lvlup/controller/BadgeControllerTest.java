package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.dto.user.UserBadgeDTO;
import com.greenfox.lvlup.model.entity.BadgeLevel;
import com.greenfox.lvlup.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BadgeController.class)
public class BadgeControllerTest {

  @Autowired
  public MockMvc mvc;

  @InjectMocks
  private BadgeController badgeController;

  @MockBean
  UserService userService;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void showBadgesTestWithCorrectHeader() throws Exception {

    UserBadgeDTO userBadgeDTO1 = new UserBadgeDTO("Process improver", 1);
    UserBadgeDTO userBadgeDTO2 = new UserBadgeDTO("English speaker", 2);
    List<UserBadgeDTO> userBadgeDTOList = new ArrayList<>();
    userBadgeDTOList.add(userBadgeDTO1);
    userBadgeDTOList.add(userBadgeDTO2);

    List<BadgeLevel> badgeLevelsMock = new ArrayList<>();
    badgeLevelsMock.add(0, new BadgeLevel(1));
    badgeLevelsMock.add(1, new BadgeLevel(2));

    when(userService.getBadges(anyLong())).thenReturn(badgeLevelsMock);
    when(userService.convertBadgeToDTO(badgeLevelsMock)).thenReturn(userBadgeDTOList);

    ResponseEntity testResult = badgeController.getUserBadges(1L, "testToken");

    assertNotNull(testResult);
    assertEquals(userBadgeDTOList, testResult.getBody());
  }

  @Test
  public void showBadgesTestWithoutToken() throws Exception {
    mvc.perform(get("/badges")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isUnauthorized())
        .andExpect(jsonPath("$.error").value("Unauthorized"));
  }
}
