package com.greenfox.lvlup.service;

import com.greenfox.lvlup.model.entity.BadgeLevel;
import com.greenfox.lvlup.model.entityTestingDto.BadgeDTO;
import com.greenfox.lvlup.model.entity.Badge;
import com.greenfox.lvlup.repositrory.BadgeLevelRepository;
import com.greenfox.lvlup.repositrory.BadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BadgeService {
    private BadgeRepository badgeRepository;
    private BadgeLevelService badgeLevelService;


    @Autowired
    public BadgeService(BadgeRepository badgeRepository, BadgeLevelService badgeLevelService) {
        this.badgeRepository = badgeRepository;
        this.badgeLevelService = badgeLevelService;
    }

    public BadgeDTO getDTOfromBadge(Badge badge) {
        BadgeDTO badgeDTO = new BadgeDTO();
        badgeDTO.version = badge.getVersion();
        badgeDTO.name = badge.getName();
        badgeDTO.tag = badge.getTag();
        badgeDTO.levels = new ArrayList<>();
        List<BadgeLevel> levels = badge.getLevels();
        for (BadgeLevel item : levels) {
            badgeDTO.levels.add(badgeLevelService.getDTOfromBadgeLevel(item));
        }
        return badgeDTO;
    }

    public List<BadgeDTO> getDTOListFromBadge() {
        List<BadgeDTO> badgeDTOs = new ArrayList<>();
        List<Badge> badges = badgeRepository.findAll();
        for (Badge badge : badges) {
            badgeDTOs.add(getDTOfromBadge(badge));
        }
        return badgeDTOs;
    }
}
