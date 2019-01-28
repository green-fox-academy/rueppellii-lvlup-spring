package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.PitchDTO;
import com.greenfox.lvlup.model.GeneralException;
import com.greenfox.lvlup.model.SuccessfulQuery;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class PitchController {

  private String successMessage2 = "{ \"myPitches\": [ { \"timestamp\": \"2018-11-29 17:10:47\", \"username\": \"balazs.barna\", " +
      "\"badgeName\": \"Programming\", \"oldLevel\": 2, \"pitchedLevel\": 3, \"pitchMessage\": " +
      "\"I improved in React, Redux, basic JS, NodeJS, Express and in LowDB, pls give me more money\", " +
      "\"holders\": [ { \"name\": \"sandor.vass\", \"message\": null, \"pitchStatus\": false },... ], } ], " +
      "\"pitchesToReview\": [ { \"timestamp\": \"2018-11-29 17:10:47\", \"username\": \"berei.daniel\", " +
      "\"badgeName\": \"English speaker\", \"oldLevel\": 2, \"pitchedLevel\": 3, \"pitchMessage\": " +
      "\"I was working abroad for six years, so I can speak english very well. Pls improve my badge level to 3.\", " +
      "\"holders\": [ { \"name\": \"balazs.jozsef\", \"message\": \"Yes, you are able to speak english\", " +
      "\"pitchStatus\": true },... ] }";

  @GetMapping("/pitches")
  public ResponseEntity getPitches(@RequestHeader HttpHeaders headers,
                                   @RequestHeader(value = "userTokenAuth", required = false) String token) throws GeneralException {
    if (headers != null && headers.getContentType().equals(MediaType.APPLICATION_JSON) && token != null && !token.equals("")) {
      return new ResponseEntity(new SuccessfulQuery(successMessage2), HttpStatus.OK);
    } else throw new GeneralException("Unauthorized", HttpStatus.UNAUTHORIZED);
  }

  @PostMapping(value = "/pitch")
  public ResponseEntity<Object> addPitch(
      @RequestHeader(value = "userTokenAuth") String token
      , HttpServletRequest request
      , @Valid @RequestBody(required = false) PitchDTO pitchDTO) throws GeneralException {
    if (token == null || token.isEmpty())
      throw new GeneralException("Unauthorized", HttpStatus.UNAUTHORIZED);
    else if (request.getContentType() == null || !request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE))
      throw new GeneralException("Unsupported media type", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    else return new ResponseEntity(new SuccessfulQuery("Success"), HttpStatus.CREATED);
  }

}
