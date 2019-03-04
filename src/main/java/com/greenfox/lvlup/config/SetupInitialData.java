package com.greenfox.lvlup.config;
<<<<<<< HEAD

=======
import com.greenfox.lvlup.model.entity.Archetype;
import com.greenfox.lvlup.model.entity.Badge;
import com.greenfox.lvlup.model.entity.BadgeLevel;
import com.greenfox.lvlup.model.entity.User;
import com.greenfox.lvlup.repositrory.ArchetypeRepository;
import com.greenfox.lvlup.repositrory.BadgeLevelRepository;
import com.greenfox.lvlup.repositrory.BadgeRepository;
import com.greenfox.lvlup.repositrory.UserRepository;
>>>>>>> 8e7fb9d2a269c5f81215e683c9aad057086c29ad
import com.greenfox.lvlup.model.entity.*;
import com.greenfox.lvlup.repositrory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Component
@Profile(value = {"dev", "prod"})
public class SetupInitialData implements CommandLineRunner {
  private BadgeRepository badgeRepository;
  private BadgeLevelRepository levelRepository;
  private UserRepository userRepository;
  private ArchetypeRepository archetypeRepository;
  private ReviewRepository reviewRepository;
  private PitchRepository pitchRepository;

  @Value("${spring.datasource.url}")
  private String dbname;

  @Autowired
  public SetupInitialData(ArchetypeRepository archetypeRepository, BadgeRepository badgeRepository,
                          BadgeLevelRepository levelRepository, UserRepository userRepository,
                          ReviewRepository reviewRepository, PitchRepository pitchRepository) {
    this.badgeRepository = badgeRepository;
    this.levelRepository = levelRepository;
    this.userRepository = userRepository;
    this.archetypeRepository = archetypeRepository;
    this.reviewRepository = reviewRepository;
    this.pitchRepository = pitchRepository;
  }

  @Override
  @Transactional
  public void run(String... args) throws Exception {
    System.out.println("****************************" + dbname);


    Badge badge1 = new Badge("2.2", "Process improver", "general");
    Badge badge2 = new Badge("2.3", "English speaker", "mentor");
    Badge badge3 = new Badge("1.2", "Feedback receiver", "general");
    Badge badge4 = new Badge("1.5", "Feedback giver", "mentor");
    badgeRepository.save(badge1);
    badgeRepository.save(badge2);
    badgeRepository.save(badge3);
    badgeRepository.save(badge4);
    User user1 = new User("Horváth Anna", "testusertoken", "base64://dwabi24632gdkje8549632...");
    User user2 = new User("Kiss József", "testadmin:token", "base64://dwabi24632gdkje8549632...");
    User user3 = new User("Antal Gábor", "verysecuretokendjawuidguowa76795432", "base64://dwabi24632gdkje8549632...");
    User user4 = new User("Szabó Tibor", "sandor542ghd237tiguk3", "base64://dwabi24632gdkje8549632...");
    User user5 = new User("Vass Imola", "verysecuretokendnj32t7853t2iugkjds", "base64://dwabi24632gdkje8549632...");
    User user6 = new User("Nagy Bence", "token123", "pic123");
    User user7 = new User("Takács Kata", "token321", "pic321");
    userRepository.save(user1);
    userRepository.save(user2);
    userRepository.save(user3);
    userRepository.save(user4);
    userRepository.save(user5);
    userRepository.save(user6);
    userRepository.save(user7);
    Archetype archetype1 = new Archetype("Junior mentor");
    Archetype archetype2 = new Archetype("Medior mentor");
    Archetype archetype3 = new Archetype("Senior mentor");
    archetypeRepository.save(archetype1);
    archetypeRepository.save(archetype2);
    archetypeRepository.save(archetype3);
    levelRepository.save(new BadgeLevel(1, "I can reliably improve processes across the organization.", badge1, user1, user2, user3, user4, user6));
    levelRepository.save(new BadgeLevel(2, "I can introduce processes that are new to the company and implement them.", badge1, archetype1));
    levelRepository.save(new BadgeLevel(3, "I can come up with brand new processes that have high impact on the company", badge1, archetype2, archetype3));
    levelRepository.save(new BadgeLevel(0, "I can see through processes and propose relevant and doable ideas for improvement.", badge1, user7));
    levelRepository.save(new BadgeLevel(1, "I can present/facilitate sessions in English yet not always fluently.", badge2, user7, user5, user2, user3));
    levelRepository.save(new BadgeLevel(2, "I can confidently present/facilitate sessions in English.", badge2, user1, user4, user6));
    levelRepository.save(new BadgeLevel(3, "I can express and understand nuanced opinions, needs and feelings.", badge2, archetype1));
    levelRepository.save(new BadgeLevel(4, "I can negotiate with clients and partners, using sophisticated, grammaticaly correct English.", badge2, archetype2, archetype3));
    levelRepository.save(new BadgeLevel(1, "I usually receive feedback without interrupting, getting defensive, trying to find excuses, lashing out.", badge3, user2, user4, user6));
    levelRepository.save(new BadgeLevel(2, "I proactively seek feedback on projects or my general .", badge3, user5, user7));
    levelRepository.save(new BadgeLevel(2, "When you give me feedback, I help yout get specific on: what I did, why could we do about it.", badge3, archetype1, archetype2));
    levelRepository.save(new BadgeLevel(2, "I am a role-model for how to receive and process feedback.", badge3, archetype3));
    levelRepository.save(new BadgeLevel(1, "I proactively give specific, easy to understand observations, and make a request to you.", badge4, archetype1));
    levelRepository.save(new BadgeLevel(0, "When I have a problem, I usually let you know by expressing it clearly instead of keeping it to myself.", badge4, user2, user3, user4));
    levelRepository.save(new BadgeLevel(2, "I decribe my feelings and needs in conflict situations with most of my team mates.", badge4, archetype2));
    levelRepository.save(new BadgeLevel(3, "I am confident in describing my feelings and needs considering the feedback receivers feelings and needs and suggest a constructive solution", badge4, archetype3));
    /*Review review1 = new Review("Yes, you are able to speak english", true, user3);
    Review review2 = new Review("Please improve your english", false, user4);
    Review review3 = new Review(true, user4);
    Review review4 = new Review("Ok, I think he is ready for this job", true, user2);
    Review review5 = new Review("Surely he can do it", true, user1);
    Review review6 = new Review(false, user5);
    Review review7 = new Review("Awesome person with awesome personality", true, user3);
    Review review8 = new Review("It cant be serious", false, user4);
    reviewRepository.save(review1);
    reviewRepository.save(review2);
    reviewRepository.save(review3);
    reviewRepository.save(review4);
    reviewRepository.save(review5);
    reviewRepository.save(review6);
    reviewRepository.save(review7);
    reviewRepository.save(review8);
    Pitch pitch1 = new Pitch(new Date(), 2, 3, "I was working abroad for six years, so I can speak english very well. Pls improve my badge level to 3.", user1, badge2);
    Pitch pitch2 = new Pitch(new Date(), 1, 5, "I can see through processes and propose relevant and doable ideas for improvement. I can create improved definition / accountibility / documentation and communicate it to the team", user1, badge1);
    Pitch pitch3 = new Pitch(new Date(), 1, 3, "I proactively seek feedback on projects or my general work and choose how to integrate it.", user2, badge3);
    Pitch pitch4 = new Pitch(new Date(), 4, 5, "When I have a problem, I usually let you know by expressing it clearly instead of keeping to myself", user3, badge4);
    Pitch pitch5 = new Pitch(new Date(), 2, 3, "I improved in React, Redux, basic JS, NodeJS, Express and in LowDB, pls give me more money", user4, badge1);
    Pitch pitch6 = new Pitch(new Date(), 4, 5, "I am confident in describing my feelings and needs considering the feedback receivers feelings and suggest a constructive solution", user5, badge2);
    pitchRepository.save(pitch1);
    pitchRepository.save(pitch2);
    pitchRepository.save(pitch3);
    pitchRepository.save(pitch4);
    pitchRepository.save(pitch5);
    pitchRepository.save(pitch6);
    review1.setPitch(pitch1);
    review2.setPitch(pitch1);
    review3.setPitch(pitch2);
    review4.setPitch(pitch2);
    review5.setPitch(pitch3);
    review6.setPitch(pitch5);
    review7.setPitch(pitch6);
    review8.setPitch(pitch4);
  }*/
  }
=======
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
@Profile(value = {"dev", "test"})
public class SetupInitialData implements CommandLineRunner {
    private BadgeRepository badgeRepository;
    private BadgeLevelRepository levelRepository;
    private UserRepository userRepository;
    private ArchetypeRepository archetypeRepository;
    private ReviewRepository reviewRepository;
    private PitchRepository pitchRepository;

    @Value("${spring.datasource.url}")
    private String dbname;

    private List<Badge> badges;
    private List<User> users;
    private List<Archetype> archetypes;
    private List<BadgeLevel> badgeLevels;
    private List<Review> reviews;
    private List<Pitch> pitches;


    @Autowired
    public SetupInitialData(ArchetypeRepository archetypeRepository, BadgeRepository badgeRepository,
                            BadgeLevelRepository levelRepository, UserRepository userRepository,
                            ReviewRepository reviewRepository, PitchRepository pitchRepository) {
        this.badgeRepository = badgeRepository;
        this.levelRepository = levelRepository;
        this.userRepository = userRepository;
        this.archetypeRepository = archetypeRepository;
        this.reviewRepository = reviewRepository;
        this.pitchRepository = pitchRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println("****************************" + dbname);


        generateBadges();
        generateUsers();
        generateArchetypes();
        generateBadgelevelsWithUsersAndArchetypes();
        generateReviews();
        generatePitches();
        setPitchesInReviews();
        saveAll();
    }

    private void generateBadges() {
        badges = Arrays.asList(
                new Badge("2.2", "Process improver", "general"),
                new Badge("2.3", "English speaker", "mentor"),
                new Badge("1.2", "Feedback receiver", "general"),
                new Badge("1.5", "Feedback giver", "mentor"));
    }

    private void generateUsers() {
        users = Arrays.asList(new User("Horváth Anna", "testusertoken", "base64://dwabi24632gdkje8549632..."),
                new User("Kiss József", "testadmin:token", "base64://dwabi24632gdkje8549632..."),
                new User("Antal Gábor", "verysecuretokendjawuidguowa76795432", "base64://dwabi24632gdkje8549632..."),
                new User("Szabó Tibor", "sandor542ghd237tiguk3", "base64://dwabi24632gdkje8549632..."),
                new User("Vass Imola", "verysecuretokendnj32t7853t2iugkjds", "base64://dwabi24632gdkje8549632..."),
                new User("Nagy Bence", "token123", "pic123"),
                new User("Takács Kata", "token321", "pic321"));
    }

    public void generateArchetypes() {
        archetypes = Arrays.asList(
                new Archetype("Junior mentor"),
                new Archetype("Medior mentor"),
                new Archetype("Senior mentor"));
    }

    public void generateBadgelevelsWithUsersAndArchetypes() {
        badgeLevels = Arrays.asList(
                new BadgeLevel(1, "I can reliably improve processes across the organization.",
                        badges.get(0), users.get(0), users.get(1), users.get(3), users.get(5)),
                new BadgeLevel(2, "I can introduce processes that are new to the company and implement them.",
                        badges.get(0), archetypes.get(0)),
                new BadgeLevel(3, "I can come up with brand new processes that have high impact on the company",
                        badges.get(0), archetypes.get(1), archetypes.get(2)),
                new BadgeLevel(0, "I can see through processes and propose relevant and doable ideas for improvement.",
                        badges.get(0), users.get(6)),
                new BadgeLevel(1, "I can present/facilitate sessions in English yet not always fluently.",
                        badges.get(1), users.get(6), users.get(4), users.get(1), users.get(2)),
                new BadgeLevel(2, "I can confidently present/facilitate sessions in English.",
                        badges.get(1), users.get(0), users.get(3), users.get(5)),
                new BadgeLevel(3, "I can express and understand nuanced opinions, needs and feelings.",
                        badges.get(1), archetypes.get(0)),
                new BadgeLevel(4, "I can negotiate with clients and partners, using sophisticated, grammaticaly correct English.",
                        badges.get(1), archetypes.get(1), archetypes.get(2)),
                new BadgeLevel(1, "I usually receive feedback without interrupting, getting defensive, trying to find excuses, lashing out.",
                        badges.get(2), users.get(1), users.get(3), users.get(5)),
                new BadgeLevel(2, "I proactively seek feedback on projects or my general .",
                        badges.get(2), users.get(4), users.get(6)),
                new BadgeLevel(2, "When you give me feedback, I help yout get specific on: what I did, why could we do about it.",
                        badges.get(2), archetypes.get(0), archetypes.get(1)),
                new BadgeLevel(2, "I am a role-model for how to receive and process feedback.",
                        badges.get(2), archetypes.get(2)),
                new BadgeLevel(1, "I proactively give specific, easy to understand observations, and make a request to you.",
                        badges.get(3), archetypes.get(0)),
                new BadgeLevel(0, "When I have a problem, I usually let you know by expressing it clearly instead of keeping it to myself.",
                        badges.get(3), users.get(1), users.get(2), users.get(3)),
                new BadgeLevel(2, "I decribe my feelings and needs in conflict situations with most of my team mates.",
                        badges.get(3), archetypes.get(1)),
                new BadgeLevel(3, "I am confident in describing my feelings and needs considering the feedback receivers feelings and needs " +
                        "and suggest a constructive solution", badges.get(3), archetypes.get(2)));
    }

    public void generateReviews() {
        reviews = Arrays.asList(
                new Review("Yes, you are able to speak english", true, users.get(2)),
                new Review("Please improve your english", false, users.get(3)),
                new Review(true, users.get(3)),
                new Review("Ok, I think he is ready for this job", true, users.get(1)),
                new Review("Surely he can do it", true, users.get(0)),
                new Review(false, users.get(4)),
                new Review("Awesome person with awesome personality", true, users.get(2)),
                new Review("It cant be serious", false, users.get(3)));
    }


    public void generatePitches() {
        pitches = Arrays.asList(
                new Pitch(new Date(), 2, 3, "I was working abroad for six years, so I can speak english very well. " +
                        "Pls improve my badge level to 3.", users.get(0), badges.get(1)),
                new Pitch(new Date(), 1, 5, "I can see through processes and propose relevant and doable ideas for improvement." +
                        " I can create improved definition / accountibility / documentation and communicate it to the team", users.get(0), badges.get(0)),
                new Pitch(new Date(), 1, 3, "I proactively seek feedback on projects or my general work and choose how " +
                        "to integrate it.", users.get(1), badges.get(2)),
                new Pitch(new Date(), 4, 5, "When I have a problem, I usually let you know by expressing it clearly " +
                        "instead of keeping to myself", users.get(2), badges.get(3)),
                new Pitch(new Date(), 2, 3, "I improved in React, Redux, basic JS, NodeJS, Express and in LowDB, " +
                        "pls give me more money", users.get(3), badges.get(0)),
                new Pitch(new Date(), 4, 5, "I am confident in describing my feelings and needs considering the feedback " +
                        "receivers feelings and suggest a constructive solution", users.get(4), badges.get(1)));
    }

    private void setPitchesInReviews() {
        reviews.get(0).setPitch(pitches.get(0));
        reviews.get(1).setPitch(pitches.get(0));
        reviews.get(2).setPitch(pitches.get(1));
        reviews.get(3).setPitch(pitches.get(1));
        reviews.get(4).setPitch(pitches.get(2));
        reviews.get(5).setPitch(pitches.get(4));
        reviews.get(6).setPitch(pitches.get(5));
        reviews.get(7).setPitch(pitches.get(3));
    }

    private void saveAll() {
        badges.forEach(b -> badgeRepository.save(b));
        users.forEach(u -> userRepository.save(u));
        archetypes.forEach(a -> archetypeRepository.save(a));
        badgeLevels.forEach(bl -> levelRepository.save(bl));
        pitches.forEach(p -> pitchRepository.save(p));
        reviews.forEach(r -> reviewRepository.save(r));
    }
>>>>>>> 8e7fb9d2a269c5f81215e683c9aad057086c29ad
}
