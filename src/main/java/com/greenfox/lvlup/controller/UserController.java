package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {
  @Autowired
  UserService service;

  @GetMapping
  public Object liseUsers(@RequestParam long id, @RequestHeader String userTokenAuth) {
    return this.service.getUserDetailsById(id);
  }


}
