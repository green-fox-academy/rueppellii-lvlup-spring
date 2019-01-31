package com.greenfox.lvlup.controller;

import com.google.api.client.json.JsonFactory;
import com.greenfox.lvlup.exception.ErrorMessage;
import com.greenfox.lvlup.exception.GeneralException;
import com.greenfox.lvlup.security.JwtGenerator;
import com.greenfox.lvlup.security.JwtUserPrinciple;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class AuthController {


  // authenticate with google, it returns a token


  @PostMapping("/auth")
  public ResponseEntity authenticate() throws Exception {

    GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier(new JwtGenerator());
    if (//authentication is not succesful ) {
    throw new GeneralException("Authentication is not succesful", HttpStatus.UNAUTHORIZED);
  } else return new ResponseEntity(new JwtUserPrinciple(token.getName()), HttpStatus.OK);
  }

}
