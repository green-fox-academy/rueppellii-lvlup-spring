package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.exception.GeneralException;
import com.greenfox.lvlup.model.mockdto.UserBadgeSetDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class BadgeController {
  private UserBadgeSetDTO testUserBadges = new UserBadgeSetDTO();

  @GetMapping(value = "/badges", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> showBadges(@RequestHeader(value = "userTokenAuth", required = false) String token) throws GeneralException {
    if (token == null || token.equals("")) {
      throw new GeneralException("Unauthorized", HttpStatus.UNAUTHORIZED);
    } return new ResponseEntity<>(testUserBadges, HttpStatus.OK);
  }
}
