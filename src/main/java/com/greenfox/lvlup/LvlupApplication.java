package com.greenfox.lvlup;

import com.greenfox.lvlup.model.entity.Badge;
import com.greenfox.lvlup.model.entity.BadgeLevel;
import com.greenfox.lvlup.model.entity.User;
import com.greenfox.lvlup.repositrory.BadgeLevelRepository;
import com.greenfox.lvlup.repositrory.BadgeRepository;
import com.greenfox.lvlup.repositrory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LvlupApplication implements CommandLineRunner {
  private BadgeRepository badgeRepository;
  private BadgeLevelRepository levelRepository;
  private UserRepository userRepository;

  @Autowired
  public LvlupApplication(BadgeRepository badgeRepository, BadgeLevelRepository levelRepository, UserRepository userRepository) {
    this.badgeRepository = badgeRepository;
    this.levelRepository = levelRepository;
    this.userRepository = userRepository;
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
    levelRepository.save(new BadgeLevel(3,"Category 3", badge1,new User("Andi"), new User("Pisti")));
    levelRepository.save(new BadgeLevel(1,"Category 1", badge1,new User("Adél"), new User("Diana")));
    levelRepository.save(new BadgeLevel(1,"Category 1", badge2,new User("Péter"), new User("Bali")));
    levelRepository.save(new BadgeLevel(3,"Category 3", badge2,new User("XYZAZ"), new User("Hanna"), new User("Kriszta")));
    levelRepository.save(new BadgeLevel(6,"Category 6", badge2,new User("Dani")));

  }
}

