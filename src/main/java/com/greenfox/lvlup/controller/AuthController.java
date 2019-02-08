package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.dto.UserBadgeSetDTO;
import com.greenfox.lvlup.security.JwtAccessToken;
import com.greenfox.lvlup.security.JwtGenerator;
import com.greenfox.lvlup.security.JwtUserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  private JwtGenerator jwtGenerator;

  public AuthController(JwtGenerator jwtGenerator) {
    this.jwtGenerator = jwtGenerator;
  }

  @GetMapping(value = "/")
  public JwtAccessToken showIfAuthenticated() {
    JwtAccessToken token = new JwtAccessToken(jwtGenerator.generate(new JwtUserDTO()));
    return token;
  }

  @GetMapping(value = "/test-endpoint")
  public ResponseEntity<?> showBadges() {
    return new ResponseEntity<>(new UserBadgeSetDTO(), HttpStatus.OK);
  }
}

