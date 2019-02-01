package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.exception.GeneralException;
import com.greenfox.lvlup.security.JwtAccessToken;
import com.greenfox.lvlup.security.JwtGenerator;
import com.greenfox.lvlup.security.JwtUserPrinciple;
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

  @GetMapping(value = "/auth")
  public ResponseEntity showIfAuthenticated() {
    JwtAccessToken token  = new JwtAccessToken(jwtGenerator.generate(new JwtUserPrinciple()));
    return new ResponseEntity<>(token, HttpStatus.OK);
  }

  // browser saves ggoogle generated token
  //calls the service to generate the token in the auth endpoint if we reach it, empty token , with only expiration (5 min) time in payload
  // response body is the jwt token (that we pass to frontend) (this is the access token, the google passed one refresh token)
  // frontend sends the jwt token in the header every time when we need it, for validation
//
//
//  @PostMapping("/auth")
//  public ResponseEntity authenticate() throws Exception {
//    // authenticate with google, it returns a token
//    GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier(new JwtGenerator());
//    if (//authentication is not succesful ) {
//    throw new GeneralException("Authentication is not succesful", HttpStatus.UNAUTHORIZED);
//  } else return new ResponseEntity(new JwtUserPrinciple(token.getName()), HttpStatus.OK);
}
