package com.greenfox.lvlup.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenfox.lvlup.model.entity.User;
import com.greenfox.lvlup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/user")
public class UserController {


  private UserRepository repo;

  public static String stringify(Object object) throws JsonProcessingException {
    return new ObjectMapper().writeValueAsString(object);
  }

  @Autowired
  public void setRepo(UserRepository repo){
    this.repo = repo;
  }

  @GetMapping
  public Object liseUsers(){
  /*  String s = "";
    try {
      List<User> list = this.repo.findAll();
      for (User u :
          list) {
        s += stringify(u);
      }
    }catch (Exception e){
      System.out.println(e.getStackTrace());
    }
    return s;*/
  return this.repo.findAll();
  }


}
