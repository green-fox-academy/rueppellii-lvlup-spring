package com.greenfox.lvlup;

import com.greenfox.lvlup.model.entity.Archetype;
import com.greenfox.lvlup.model.entity.Badge;
import com.greenfox.lvlup.model.entity.BadgeLevel;
import com.greenfox.lvlup.repositrory.ArchetypeRepository;
import com.greenfox.lvlup.repositrory.BadgeLevelRepository;
import com.greenfox.lvlup.repositrory.BadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LvlupApplication implements CommandLineRunner {

  public static void main(String[] args) {
        SpringApplication.run(LvlupApplication.class, args);
    }

  @Override
  public void run(String... args) throws Exception {
  }
}


