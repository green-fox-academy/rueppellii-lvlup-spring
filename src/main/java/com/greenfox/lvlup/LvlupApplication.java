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

  ArchetypeRepository archetypeRepository;
  BadgeLevelRepository levelRepository;
  BadgeRepository badgeRepository;

  @Autowired
  public LvlupApplication(ArchetypeRepository archetypeRepository, BadgeLevelRepository levelRepository, BadgeRepository badgeRepository) {
    this.archetypeRepository = archetypeRepository;
    this.levelRepository = levelRepository;
    this.badgeRepository = badgeRepository;
  }

  public static void main(String[] args) {
        SpringApplication.run(LvlupApplication.class, args);
    }

  @Override
  public void run(String... args) throws Exception {

    Badge badge1 = new Badge("3.2", "testEnglish", "TESTING");
    Badge badge2 = new Badge("2.2", "testPresentation skills", "prezi");
    Badge badge3 = new Badge("1.2", "testCommunication skills", "communication");
    badgeRepository.save(badge1);
    badgeRepository.save(badge2);
    badgeRepository.save(badge3);

    Archetype arh1 = new Archetype("Junior mentor");
    Archetype arh2 = new Archetype("Senior mentor");
    archetypeRepository.save(arh1);
    archetypeRepository.save(arh2);

    levelRepository.save(new BadgeLevel(5, "Category 5", badge2, arh1, arh2));
    levelRepository.save(new BadgeLevel(3, "Category 3", badge2, arh1));
  }
}


