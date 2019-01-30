package com.greenfox.lvlup.exception;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class ValidationErrorBuilder {

  public static ValidationError fromBindingErrors(Errors errors) {
    ValidationError error = new ValidationError("Please provide all fields: " + errors.getErrorCount() + " error(s)");
    for (ObjectError objectError : errors.getAllErrors()) {
      error.addValidationError(objectError.getDefaultMessage());
    }
    return error;
  }
}
