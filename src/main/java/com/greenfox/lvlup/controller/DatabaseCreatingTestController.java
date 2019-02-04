package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.dto.BadgeDTO;
import com.greenfox.lvlup.model.entity.Badge;
import com.greenfox.lvlup.model.entity.User;
import com.greenfox.lvlup.repositrory.BadgeRepository;
import com.greenfox.lvlup.repositrory.UserRepository;
import com.greenfox.lvlup.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DatabaseCreatingTestController {
    private BadgeRepository badgeRepository;
    private UserRepository userRepository;
    private BadgeService badgeService;

    @Autowired
    public DatabaseCreatingTestController(BadgeRepository badgeRepository, UserRepository userRepository, BadgeService badgeService) {
        this.badgeRepository = badgeRepository;
        this.userRepository = userRepository;
        this.badgeService = badgeService;
    }

    @GetMapping("/testbadgedtos")
    public List<BadgeDTO> getAllDtos() {
       return badgeService.getDTOListFromEntities();
    }

    @GetMapping("/testbadges")
    public List<Badge> getAll() {
        return badgeRepository.findAll();
    }
    @GetMapping("/testusers")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}

