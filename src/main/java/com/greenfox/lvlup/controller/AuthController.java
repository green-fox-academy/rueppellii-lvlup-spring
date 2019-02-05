package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.security.JwtGenerator;
import com.greenfox.lvlup.security.JwtUserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  private JwtGenerator jwtGenerator;

  public AuthController(JwtGenerator jwtGenerator) {
    this.jwtGenerator = jwtGenerator;
  }

  @GetMapping(value = "/auth")
  public String showIfAuthenticated() {
    String token = jwtGenerator.generate(new JwtUserDTO());
    return token;
  }
}

  // browser saves google generated token
  //calls the service to generate the token in the auth endpoint if we reach it, empty token , with only expiration (5 min) time in payload
  // response body is the jwt token (that we pass to frontend) (this is the access token, the google passed one is the refresh token)
  // frontend sends the jwt token in the header every time when we need it, for validation
//
//
