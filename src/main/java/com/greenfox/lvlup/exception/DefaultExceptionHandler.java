package com.greenfox.lvlup.exception;

import com.greenfox.lvlup.model.GeneralException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(GeneralException.class)
  public ResponseEntity<?> handleAllExceptions(GeneralException e){
    return new ResponseEntity<>(e.getErrorMessage(), e.getHttpStatus());
  }
}
