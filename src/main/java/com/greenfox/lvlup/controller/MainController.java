package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.GeneralException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.greenfox.lvlup.model.*;
import com.greenfox.lvlup.model.ValidationError;
import com.greenfox.lvlup.model.Badge;
import com.greenfox.lvlup.model.SuccessfulQuery;
import com.greenfox.lvlup.service.ValidationErrorBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class MainController {
  private String successMessage1 = "{ \"badges\": [ { \"name\": \"Process improver\", \"level\": \"2\" }, { \"name\": " +
      "\"English speaker\", \"level\": \"1\" }, { \"name\": \"Feedback giver\", \"level\": \"1\" } ] }";

  private String successMessage2 = "{ \"myPitches\": [ { \"timestamp\": \"2018-11-29 17:10:47\", \"username\": \"balazs.barna\", " +
      "\"badgeName\": \"Programming\", \"oldLevel\": 2, \"pitchedLevel\": 3, \"pitchMessage\": " +
      "\"I improved in React, Redux, basic JS, NodeJS, Express and in LowDB, pls give me more money\", " +
      "\"holders\": [ { \"name\": \"sandor.vass\", \"message\": null, \"pitchStatus\": false },... ], } ], " +
      "\"pitchesToReview\": [ { \"timestamp\": \"2018-11-29 17:10:47\", \"username\": \"berei.daniel\", " +
      "\"badgeName\": \"English speaker\", \"oldLevel\": 2, \"pitchedLevel\": 3, \"pitchMessage\": " +
      "\"I was working abroad for six years, so I can speak english very well. Pls improve my badge level to 3.\", " +
      "\"holders\": [ { \"name\": \"balazs.jozsef\", \"message\": \"Yes, you are able to speak english\", " +
      "\"pitchStatus\": true },... ] }";

  @GetMapping("/badges")
  public ResponseEntity<?> showBadges(@RequestHeader() HttpHeaders header,
                                      @RequestHeader(value = "userTokenAuth", required = false) String token) throws GeneralException {
    if (header != null
        && header.getContentType().equals(MediaType.APPLICATION_JSON)
        && token != null
        && !token.equals("")) {
      return new ResponseEntity(new SuccessfulQuery(successMessage1), HttpStatus.OK);
    } else throw new GeneralException("Unauthorized", HttpStatus.UNAUTHORIZED);
  }

  @GetMapping("/pitches")
  public ResponseEntity getPitches(@RequestHeader HttpHeaders headers,
                                   @RequestHeader(value = "userTokenAuth", required = false) String token) throws GeneralException {
    if (headers != null && headers.getContentType().equals(MediaType.APPLICATION_JSON) && token != null && !token.equals("")) {
      return new ResponseEntity(new SuccessfulQuery(successMessage2), HttpStatus.OK);
    } else throw new GeneralException("Unauthorized", HttpStatus.UNAUTHORIZED);
  }

  @PostMapping(value = "/pitch")
  public ResponseEntity<Object> pitchBadge(
      @RequestHeader(value = "userTokenAuth") String token
      , HttpServletRequest request
      , @Valid @RequestBody(required = false) Badge badge) throws GeneralException {
    if (token == null || token.isEmpty())
      throw new GeneralException("Unauthorized", HttpStatus.UNAUTHORIZED);
    else if (request.getContentType() == null || !request.getContentType().equals(MediaType.APPLICATION_JSON))
      throw new GeneralException("Unsupported media type", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    else return new ResponseEntity(new SuccessfulQuery("Success"), HttpStatus.CREATED);
  }

  @PostMapping(value = "/admin/add", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<?> addBadge(@Valid @RequestBody BadgeDTO dtoToAdd,
                                    @RequestHeader(value = "Content-Type") HttpHeaders header,
                                    @RequestHeader(value = "userTokenAuth", required = false, defaultValue = "") String token) throws Exception {
    if (header == null || !header.getContentType().equals(MediaType.APPLICATION_JSON)
        || token == null || token.equals("")
        || dtoToAdd == null) {
      throw new GeneralException("Please provide all fields", HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity(new SuccessfulQuery("Success"), HttpStatus.CREATED);

  }

/*  @ExceptionHandler
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ValidationError handleException(MethodArgumentNotValidException exception) {
    return createValidationError(exception);
  }

  private ValidationError createValidationError(MethodArgumentNotValidException exception) {
    return ValidationErrorBuilder.fromBindingErrors(exception.getBindingResult());
  }*/


}
