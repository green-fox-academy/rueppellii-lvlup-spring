package com.greenfox.lvlup.config;

import com.greenfox.lvlup.model.entity.Archetype;
import com.greenfox.lvlup.model.entity.Badge;
import com.greenfox.lvlup.model.entity.BadgeLevel;
import com.greenfox.lvlup.model.entity.User;
import com.greenfox.lvlup.repositrory.ArchetypeRepository;
import com.greenfox.lvlup.repositrory.BadgeLevelRepository;
import com.greenfox.lvlup.repositrory.BadgeRepository;
import com.greenfox.lvlup.repositrory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;

@Component
@Profile(value = {"dev", "prod"})
public class SetupInitialData implements CommandLineRunner {
    private BadgeRepository badgeRepository;
    private BadgeLevelRepository levelRepository;
    private UserRepository userRepository;
    private ArchetypeRepository archetypeRepository;

    @Value("${spring.datasource.url}")
    private String dbname;

    @Autowired
    public SetupInitialData(ArchetypeRepository archetypeRepository, BadgeRepository badgeRepository, BadgeLevelRepository levelRepository, UserRepository userRepository) {
        this.badgeRepository = badgeRepository;
        this.levelRepository = levelRepository;
        this.userRepository = userRepository;
        this.archetypeRepository = archetypeRepository;
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
        User user1 = new User("Horváth Anna","testusertoken","base64://dwabi24632gdkje8549632...");
        User user2 = new User("Kiss József","testadmin:token","base64://dwabi24632gdkje8549632...");
        User user3 = new User("Antal Gábor","verysecuretokendjawuidguowa76795432","base64://dwabi24632gdkje8549632...");
        User user4 = new User("Szabó Tibor","sandor542ghd237tiguk3","base64://dwabi24632gdkje8549632...");
        User user5 = new User("Vass Imola","verysecuretokendnj32t7853t2iugkjds","base64://dwabi24632gdkje8549632...");
        User user6 = new User("Nagy Bence","token123","pic123");
        User user7 = new User("Takács Kata","token321","pic321");
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



    }
}
