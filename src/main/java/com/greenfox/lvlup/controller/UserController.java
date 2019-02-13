package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.dto.user.UserDto;
import com.greenfox.lvlup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {
  @Autowired
  UserService service;

  @GetMapping
  public ResponseEntity<UserDto> getUserDetails(@RequestParam long id, @RequestHeader String userTokenAuth) {
    return new ResponseEntity<>(service.getUserDetailsById(id), HttpStatus.OK);
  }
}
