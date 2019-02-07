package com.greenfox.lvlup.service;

import com.greenfox.lvlup.model.entity.BadgeLevel;
import com.greenfox.lvlup.model.entity.User;
import com.greenfox.lvlup.model.dto.BadgeLevelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class BadgeLevelService {
    private UserService userService;

    @Autowired
    public BadgeLevelService(UserService userService) {
        this.userService = userService;
    }

    public BadgeLevelDTO getDTOfromBadgeLevel(BadgeLevel badgeLevel) {
        BadgeLevelDTO badgeLevelDTO = new BadgeLevelDTO();
        badgeLevelDTO.level = badgeLevel.getLevel();
        badgeLevelDTO.description = badgeLevel.getDescription();
        badgeLevelDTO.holders = new HashSet<>();
        Set<User> usersHoldingBadgeLevel = badgeLevel.getHolders();
        for (User item : usersHoldingBadgeLevel) {
            badgeLevelDTO.holders.add(userService.getDTOfromUser(item));
        }
        return badgeLevelDTO;
    }
}
