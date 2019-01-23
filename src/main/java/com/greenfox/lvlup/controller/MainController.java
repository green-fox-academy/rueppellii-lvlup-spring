package com.greenfox.lvlup.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
  String successMessage = "{ \"badges\": [ { \"name\": \"Process improver\", \"level\": \"2\" }, { \"name\": " +
      "\"English speaker\", \"level\": \"1\" }, { \"name\": \"Feedback giver\", \"level\": \"1\" } ] }";

  @GetMapping("/badges")
  public ResponseEntity<String> showBadges(@RequestHeader() HttpHeaders header,
                                           @RequestHeader(value = "userTokenAuth", required = false) String token ) {
    if (header != null && header.getContentType().equals(MediaType.APPLICATION_JSON) && token != null && !token.equals("")) {
      return new ResponseEntity<>(successMessage, HttpStatus.OK);
    } else return new ResponseEntity<>("error: Unauthorized", HttpStatus.UNAUTHORIZED);
  }
}
