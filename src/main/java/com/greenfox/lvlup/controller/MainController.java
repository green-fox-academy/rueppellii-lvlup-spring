package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.error.ValidationError;
import com.greenfox.lvlup.model.Badge;
import com.greenfox.lvlup.success.SuccessfulQuery;
import org.sonar.api.server.authentication.UnauthorizedException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class MainController {

  @PostMapping(value = "/pitch")
  public ResponseEntity<Object> pitchBadge(
      @RequestHeader(value = "userTokenAuth") String token
      , HttpServletRequest request
      , @Valid @RequestBody(required = false) Badge badge
  ) {
    if (token == null || token.isEmpty())
      return new ResponseEntity(new ValidationError("Unauthorized"), HttpStatus.UNAUTHORIZED);
    else if(request.getContentType() == null || !request.getContentType().equals("application/json"))
      return new ResponseEntity(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    else return new ResponseEntity(new SuccessfulQuery("Success"), HttpStatus.CREATED);
  }

/*  @ExceptionHandler
  @ResponseStatus(value = HttpStatus.)
  public ValidationError handleException(UnauthorizedException e) {
    return new ValidationError("Unauthorized");
    //do something with the validation message: error.getBindingResult()
  }*/

}
