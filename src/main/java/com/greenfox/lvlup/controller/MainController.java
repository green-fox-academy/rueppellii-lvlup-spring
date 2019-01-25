package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.Badge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
public class MainController {
  @GetMapping(value = {"", "/"})
  public String index(){
    return "Hello";
  }


  @PostMapping("/pitch")
  //@ExceptionHandler
  //@ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ResponseEntity pitchBadge(
      @RequestHeader(value = "userTokenAuth") String token
      ,HttpServletRequest request
   //,@Valid @RequestBody(required = false) Badge badge
  ) {
    System.out.println("****************"+request.getContentType()+"****************");
    if (request.getContentType() == null || !request.getContentType().equals("application/json") || token == null || token.isEmpty())
      return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    else return new ResponseEntity(HttpStatus.CREATED);
  }

  /*@ExceptionHandler
  @ResponseBody
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public Error handleException(MethodArgumentNotValidException exception) {
    return new Error(exception.getMessage());
    //do something with the validation message: exception.getBindingResult()
  }*/

}
