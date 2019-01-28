package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.GeneralException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
public class BadgeController {

  private UserBadgeSetDTO testUserBadges = new UserBadgeSetDTO();

  @GetMapping(value = "/badges", produces = "application/json")
  public ResponseEntity<?> showBadges(@RequestHeader() HttpHeaders header,
                                      @RequestHeader(value = "userTokenAuth", required = false) String token) throws GeneralException {
    if (header != null
        && token != null
        && !token.equals("")) {
      return new ResponseEntity<>(testUserBadges, HttpStatus.OK);
    } else throw new GeneralException("Unauthorized", HttpStatus.UNAUTHORIZED);
  }
}
