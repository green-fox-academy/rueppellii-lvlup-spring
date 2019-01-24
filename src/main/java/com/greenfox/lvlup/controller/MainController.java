package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.BadgeDTO;
import com.greenfox.lvlup.model.ValidationError;
import com.greenfox.lvlup.service.ValidationErrorBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class MainController {
   @PostMapping(value= "/admin/add", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity <String>addBadge(@Valid @RequestBody BadgeDTO dtoToAdd,
                                    @RequestHeader(value = "Content-Type") HttpHeaders header,
                                    @RequestHeader(value = "userTokenAuth", required = false, defaultValue = "") String token) {
    if (header == null || !header.getContentType().equals(MediaType.APPLICATION_JSON)
        || token == null || token.equals("")
        || dtoToAdd == null){
      return new ResponseEntity<>("\"error\": \"Please provide all fields\"", HttpStatus.NOT_FOUND);

    } else return new ResponseEntity<>("\"message\": \"Success\"", HttpStatus.CREATED);

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


