package com.greenfox.lvlup;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenfox.lvlup.model.entity.Badge;
import com.greenfox.lvlup.model.entity.User;
import com.greenfox.lvlup.repository.BadgeRepository;
import com.greenfox.lvlup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;


@SpringBootApplication
public class LvlupApplication implements CommandLineRunner {
  @Autowired
  private UserRepository repo;

  @Autowired
  private BadgeRepository repo1;



  public static void main(String[] args) {
    SpringApplication.run(LvlupApplication.class, args);
  }

  Badge badge = new Badge("1.0", "english", "?", 3, null);
//  Badge badge1 = new Badge("1.0", "german", "?", 2, null);
  User user1 = new User("Peter", "token", "pic", new ArrayList<Badge>(Arrays.asList(badge)));

  @Override
  public void run(String... args) throws Exception {
    this.repo1.save(badge);
//    this.repo1.save(badge1);
   this.repo.save(user1);
  }
}

