package com.greenfox.lvlup;

import com.greenfox.lvlup.model.entity.Badge;
import com.greenfox.lvlup.model.entity.BadgeLevel;
import com.greenfox.lvlup.model.entity.User;
import com.greenfox.lvlup.repository.BadgeRepository;
import com.greenfox.lvlup.repository.LevelRepository;
import com.greenfox.lvlup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LvlupApplication implements CommandLineRunner {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BadgeRepository badgeRepository;

  @Autowired
  private LevelRepository levelRepository;

  public static void main(String[] args) {
    SpringApplication.run(LvlupApplication.class, args);
  }



  @Override
  public void run(String... args) throws Exception {
    String token = "token123";
    String pic = "picture";
    Badge badge1 = new Badge("3.2", "testEnglish", "TESTING");
    Badge badge2 = new Badge("2.2", "testPresentation skills", "prezi");
    Badge badge3 = new Badge("1.2", "testCommunication skills", "communication");
    User user1 = new User("Peter", token, pic);
    User user2 = new User("Adel", token, pic);
    User user3 = new User("Diana", token, pic);
    User user4 = new User("Bali", token, pic);
    User user5 = new User("Istvan", token, pic);
    BadgeLevel badgeLevel1 = new BadgeLevel(1, "", badge1, user5);
    BadgeLevel badgeLevel2 = new BadgeLevel(2, "Category 3", badge2, user1, user2);
    BadgeLevel badgeLevel3 = new BadgeLevel(1, "Category 3", badge3, user3, user4);
    badgeRepository.save(badge1);
    badgeRepository.save(badge2);
    badgeRepository.save(badge3);
    userRepository.save(user1);
    userRepository.save(user2);
    userRepository.save(user3);
    userRepository.save(user4);
    userRepository.save(user5);
    levelRepository.save(badgeLevel1);
    levelRepository.save(badgeLevel2);
    levelRepository.save(badgeLevel3);

  }
}

