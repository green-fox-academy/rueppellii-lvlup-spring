package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.CustomException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
  private String successMessage = "{ \"badges\": [ { \"name\": \"Process improver\", \"level\": \"2\" }, { \"name\": " +
      "\"English speaker\", \"level\": \"1\" }, { \"name\": \"Feedback giver\", \"level\": \"1\" } ] }";

  @GetMapping("/badges")
  public ResponseEntity<?> showBadges(@RequestHeader() HttpHeaders header,
                                      @RequestHeader(value = "userTokenAuth", required = false) String token) throws Exception {
    if (header != null
        && header.getContentType().equals(MediaType.APPLICATION_JSON)
        && token != null
        && !token.equals("")) {
      return new ResponseEntity<>(successMessage, HttpStatus.OK);
    } else throw new CustomException("Unauthorized", HttpStatus.UNAUTHORIZED);
  }
}
