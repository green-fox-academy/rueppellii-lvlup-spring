package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.exception.GeneralException;
import com.greenfox.lvlup.model.dto.user.UserBadgeDTO;
import com.greenfox.lvlup.model.entity.BadgeLevel;
import com.greenfox.lvlup.model.mockdto.UserBadgeSetDTO;
import com.greenfox.lvlup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
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

  @GetMapping(value = "/mockBadges", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> showBadges(@RequestHeader(value = "userTokenAuth", required = false) String token) throws GeneralException {
    if (token == null || token.equals("")) {
      throw new GeneralException("Unauthorized", HttpStatus.UNAUTHORIZED);
    }
    return new ResponseEntity<>(testUserBadges, HttpStatus.OK);
  }

  @GetMapping("/badges")
  public ResponseEntity<Object> getUserBadges(@RequestParam (required = false) Long id, @RequestHeader(value = "userTokenAuth", required = false) String token) throws GeneralException {
    if (token == null || token.equals("")) {
      throw new GeneralException("Unauthorized", HttpStatus.UNAUTHORIZED);
    }
    List<BadgeLevel> badges = userService.getBadges(id);
    List<UserBadgeDTO> badgeDTOS = userService.convertBadgeToDTO(badges);
    return new ResponseEntity<>(badgeDTOS, HttpStatus.OK);
  }
}
