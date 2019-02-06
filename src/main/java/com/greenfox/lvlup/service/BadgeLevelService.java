package com.greenfox.lvlup.service;

import com.greenfox.lvlup.model.entity.Badge;
import com.greenfox.lvlup.model.entity.BadgeLevel;
import com.greenfox.lvlup.model.entity.User;
import com.greenfox.lvlup.model.entityTestingDto.BadgeDTO;
import com.greenfox.lvlup.model.entityTestingDto.BadgeLevelDTO;
import com.greenfox.lvlup.repositrory.BadgeLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        Set<User> usersOfTheBadgeLevel = badgeLevel.getHolders();
        for (User item : usersOfTheBadgeLevel) {
            badgeLevelDTO.holders.add(userService.getDTOfromUser(item));
        }
        return badgeLevelDTO;
    }

}
