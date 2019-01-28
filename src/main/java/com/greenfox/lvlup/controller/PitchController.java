package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RestControllerAdvice
public class PitchController {

  private MockingElements mockingElements = new MockingElements();

  @GetMapping(value = "/pitches")
  public ResponseEntity getPitches(@RequestHeader(value = "userTokenAuth", required = false) String token) throws UnauthorizedException {
    if (token != null && !token.equals("")) {
      return new ResponseEntity<>(mockingElements.getSucessful(), HttpStatus.OK);
    } else throw new UnauthorizedException();
  }

  @ExceptionHandler(UnauthorizedException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ErrorMessage unauthorizedHandler() {
    return new ErrorMessage("unauthorized");
  }
}