//package com.greenfox.lvlup.controller;
//
//import com.greenfox.lvlup.exception.ErrorMessage;
//import com.greenfox.lvlup.exception.GeneralException;
//import com.greenfox.lvlup.model.*;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RestControllerAdvice
//public class PitchController {
//
//  private PitchSetDTO pitchSetDTO = new PitchSetDTO();
//
//  @GetMapping("/pitches")
//  public ResponseEntity getPitches(@RequestHeader(value = "userTokenAuth", required = false) String token) throws GeneralException {
//    if (token != null && !token.equals("")) {
//      return new ResponseEntity<>(pitchSetDTO, HttpStatus.OK);
//    } else throw new GeneralException("Unauthorized", HttpStatus.UNAUTHORIZED);
//  }
//
//  @ExceptionHandler(GeneralException.class)
//  @ResponseStatus(HttpStatus.UNAUTHORIZED)
//  public ErrorMessage unauthorizedHandler() {
//    return new ErrorMessage("unauthorized");
//  }
//}