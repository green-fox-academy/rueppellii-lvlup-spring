package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.CustomException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class BadgeController {

  private UserBadgeSetDTO testUserBadges = new UserBadgeSetDTO();

  @GetMapping(value = "/badges", consumes = "application/json")
  public ResponseEntity<?> showBadges(@RequestHeader() HttpHeaders header,
                                      @RequestHeader(value = "userTokenAuth", required = false) String token) throws CustomException {
    if (header != null
        && token != null
        && !token.equals("")) {
      return new ResponseEntity<>(testUserBadges, HttpStatus.OK);
    } else throw new CustomException("Unauthorized", HttpStatus.UNAUTHORIZED);
  }
}
