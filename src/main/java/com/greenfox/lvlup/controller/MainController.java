package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.*;
import com.greenfox.lvlup.service.ValidationErrorBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RestControllerAdvice
public class MainController {

  private String sucessful = "{ \"myPitches\": [ { \"timestamp\": \"2018-11-29 17:10:47\", \"username\": \"balazs.barna\", " +
      "\"badgeName\": \"Programming\", \"oldLevel\": 2, \"pitchedLevel\": 3, \"pitchMessage\": " +
      "\"I improved in React, Redux, basic JS, NodeJS, Express and in LowDB, pls give me more money\", " +
      "\"holders\": [ { \"name\": \"sandor.vass\", \"message\": null, \"pitchStatus\": false },... ], } ], " +
      "\"pitchesToReview\": [ { \"timestamp\": \"2018-11-29 17:10:47\", \"username\": \"berei.daniel\", " +
      "\"badgeName\": \"English speaker\", \"oldLevel\": 2, \"pitchedLevel\": 3, \"pitchMessage\": " +
      "\"I was working abroad for six years, so I can speak english very well. Pls improve my badge level to 3.\", " +
      "\"holders\": [ { \"name\": \"balazs.jozsef\", \"message\": \"Yes, you are able to speak english\", " +
      "\"pitchStatus\": true },... ] }";

  public String getSucessful() {
    return sucessful;
  }


  @GetMapping("/pitches")
  public ResponseEntity getPitches(@RequestHeader HttpHeaders headers,
                                   @RequestHeader(value = "userTokenAuth", required = false) String token) throws UnauthorizedException {
    if (headers != null && headers.getContentType().equals(MediaType.APPLICATION_JSON) && token != null && !token.equals("")) {
      return new ResponseEntity(sucessful, HttpStatus.OK);
    } else throw new UnauthorizedException();
  }

  @ResponseBody
  @ExceptionHandler(UnauthorizedException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ErrorMessage unauthorizedHandler() {
    return new ErrorMessage("unauthorized");
  }

  @PostMapping(value = "/admin/add", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<?> addBadge(@Valid @RequestBody BadgeDTO dtoToAdd,
                                    @RequestHeader(value = "Content-Type") HttpHeaders header,
                                    @RequestHeader(value = "userTokenAuth", required = false, defaultValue = "") String token) throws Exception {
    if (header == null || !header.getContentType().equals(MediaType.APPLICATION_JSON)
        || token == null || token.equals("")
        || dtoToAdd == null) {
      throw new CustomException("Please provide all fields", HttpStatus.NOT_FOUND);

    }
    return new ResponseEntity<>("\"message\": \"Success\"", HttpStatus.CREATED);

  }
  @ExceptionHandler
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ValidationError handleException(MethodArgumentNotValidException exception) {
    return createValidationError(exception);
  }

  private ValidationError createValidationError(MethodArgumentNotValidException exception) {
    return ValidationErrorBuilder.fromBindingErrors(exception.getBindingResult());
  }
}