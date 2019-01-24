package com.greenfox.lvlup.Models;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandler extends Exception {

  private ErrorMessage errorMessage;

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ErrorMessage> error(ErrorMessage ex) {
    ErrorMessage exceptionResponse = new ErrorMessage(ex.getError(), ex.getStatus());

    return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
  }

  public DefaultExceptionHandler() {
  }

  public DefaultExceptionHandler(ErrorMessage errorMessage) {
    this.errorMessage = errorMessage;
  }

  public ErrorMessage getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(ErrorMessage errorMessage) {
    this.errorMessage = errorMessage;
  }
}
