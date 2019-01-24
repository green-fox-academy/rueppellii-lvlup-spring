package com.greenfox.lvlup.exception;

import com.greenfox.lvlup.model.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(CustomException.class)
  @ResponseBody
  public ResponseEntity<?> handleAllExceptions(CustomException e){
    return new ResponseEntity<>(e.getErrorMessage(), e.getHttpStatus());
  }
}
