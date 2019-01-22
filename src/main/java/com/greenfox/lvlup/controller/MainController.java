package com.greenfox.lvlup.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class MainController {
  String successMessage = "{\n" +
      "  \"badges\": [\n" +
      "    {\n" +
      "      \"name\": \"Process improver\",\n" +
      "      \"level\": \"2\"\n" +
      "    },\n" +
      "    {\n" +
      "      \"name\": \"English speaker\",\n" +
      "      \"level\": \"1\"\n" +
      "    },\n" +
      "    {\n" +
      "      \"name\": \"Feedback giver\",\n" +
      "      \"level\": \"1\"\n" +
      "    }\n" +
      "  ]\n" +
      "}";

  @GetMapping("/badges")
  public ResponseEntity<String> showBadges(@RequestHeader() HttpHeaders header,
                                           @RequestHeader(value = "userTokenAuth", required = false) String token ) {
    if (header != null && header.getContentType().equals(MediaType.APPLICATION_JSON) && token != null && !token.equals("")) {
      return new ResponseEntity<>(successMessage, HttpStatus.OK);
    } else return new ResponseEntity<>("error: Unauthorized", HttpStatus.UNAUTHORIZED);
  }
}
