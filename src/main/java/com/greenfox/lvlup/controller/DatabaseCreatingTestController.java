package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.entity.Badge;
import com.greenfox.lvlup.repositrory.BadgeLevelRepository;
import com.greenfox.lvlup.repositrory.BadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DatabaseCreatingTestController {
    private BadgeRepository badgeRepository;

    @Autowired
    public DatabaseCreatingTestController(BadgeRepository badgeRepository, BadgeLevelRepository levelRepository) {
        this.badgeRepository = badgeRepository;

    }

    @GetMapping("/testbadges")
    public List<Badge> getAll() {
        return badgeRepository.findAll();
    }
}

