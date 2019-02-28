package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.dto.library.BadgeDTO;
import com.greenfox.lvlup.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LibraryTestController {
    private BadgeService badgeService;

    @Autowired
    public LibraryTestController(BadgeService badgeService) {
        this.badgeService = badgeService;
    }

    @GetMapping("/library")
    public List<BadgeDTO> getAllDTOs() {
        return badgeService.convertBadgeToBadgeDTO();
    }
}

