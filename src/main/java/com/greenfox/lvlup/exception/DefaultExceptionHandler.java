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

  @ExceptionHandler
  public ResponseEntity<Object> handleAuthenticationException(Exception ex){
    CustomAuthenticationException e = new CustomAuthenticationException(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,                                                                HttpStatus status, WebRequest request) {
    return new ResponseEntity<>(ValidationErrorBuilder.fromBindingErrors(ex.getBindingResult()), HttpStatus.BAD_REQUEST);
  }

//  @ExceptionHandler(ServletException.class)
//  public ResponseEntity<Object> handleRuntimeException(ServletException e) {
//    return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
//
//  }
//
//  @ExceptionHandler(MalformedJwtException.class)
//  public ResponseEntity<ErrorMessage> handleMalformedJwtException() {
//    return new ResponseEntity<>(new ErrorMessage("Unauthorized"), HttpStatus.UNAUTHORIZED);
//  }
}
