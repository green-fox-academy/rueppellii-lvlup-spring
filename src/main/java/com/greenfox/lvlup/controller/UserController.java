package com.greenfox.lvlup.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenfox.lvlup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RestController("/user")
public class UserController {

  private UserRepository repo;

  @Autowired
  public void setRepo(UserRepository repo) {
    this.repo = repo;
  }

  @GetMapping
  public Object liseUsers() {
    return this.repo.findAll();
  }


}
