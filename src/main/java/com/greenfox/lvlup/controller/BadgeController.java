package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.exception.GeneralException;
import com.greenfox.lvlup.model.dto.library.BadgeDTO;
import com.greenfox.lvlup.model.dto.user.UserBadgeDTO;
import com.greenfox.lvlup.model.dto.user.UserDto;
import com.greenfox.lvlup.model.entity.Badge;
import com.greenfox.lvlup.model.entity.BadgeLevel;
import com.greenfox.lvlup.model.entity.User;
import com.greenfox.lvlup.model.mockdto.UserBadgeSetDTO;
import com.greenfox.lvlup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
public class BadgeController {

  private UserBadgeSetDTO testUserBadges = new UserBadgeSetDTO();

  private UserService userService;

  @Autowired
  public BadgeController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping(value = "/badges", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> showBadges(@RequestHeader(value = "userTokenAuth", required = false) String token) throws GeneralException {
    if (token == null || token.equals("")) {
      throw new GeneralException("Unauthorized", HttpStatus.UNAUTHORIZED);
    }
    return new ResponseEntity<>(testUserBadges, HttpStatus.OK);
  }

  @GetMapping("/userbadges")
  public List<UserBadgeDTO> getUserBadges(@RequestParam Long id, @RequestHeader(value = "userTokenAuth", required = false) String token) throws GeneralException {
    if (token == null || token.equals("")) {
      throw new GeneralException("Unauthorized", HttpStatus.UNAUTHORIZED);
    }
    List<BadgeLevel> badges = userService.getBadges(id);
    List<UserBadgeDTO> badgeDTOS = userService.convertBadgeToDTO(badges);
    return badgeDTOS;
  }
}
