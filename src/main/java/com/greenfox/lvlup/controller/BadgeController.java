package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.exception.GeneralException;
import com.greenfox.lvlup.model.dto.UserBadgeSetDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class BadgeController {

  private UserBadgeSetDTO testUserBadges = new UserBadgeSetDTO();

  @GetMapping(value = "/api/badges", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> showBadges(@RequestHeader(value = "userTokenAuth", required = false) String token) throws Exception {
    if (token == null || token.equals("")) {
      throw new GeneralException("Unauthorized", HttpStatus.UNAUTHORIZED);
    } else return new ResponseEntity<>(testUserBadges, HttpStatus.OK);
  }
}
