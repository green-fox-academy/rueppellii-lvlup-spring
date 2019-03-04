package com.greenfox.lvlup.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(GeneralException.class)
  public ResponseEntity<Object> handleAllExceptions(GeneralException e){
    return new ResponseEntity<>(e.getErrorMessage(), e.getHttpStatus());
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                HttpStatus status, WebRequest request) {
    return new ResponseEntity<>(ValidationErrorBuilder.fromBindingErrors(ex.getBindingResult()), HttpStatus.BAD_REQUEST);
  }
}
