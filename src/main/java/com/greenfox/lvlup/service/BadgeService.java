package com.greenfox.lvlup.service;

import com.greenfox.lvlup.model.entity.BadgeLevel;
import com.greenfox.lvlup.model.entityTestingDto.BadgeSetDTO;
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
    private BadgeLevelRepository badgeLevelRepository;
    private BadgeLevelService badgeLevelService;


    @Autowired
    public BadgeService(BadgeRepository badgeRepository, BadgeLevelRepository badgeLevelRepository, BadgeLevelService badgeLevelService) {
        this.badgeRepository = badgeRepository;
        this.badgeLevelRepository = badgeLevelRepository;
        this.badgeLevelService = badgeLevelService;
    }

    //3. solution

    public BadgeDTO getDTOfromBadge(Badge badge) {
        BadgeDTO badgeDTO = new BadgeDTO();
        badgeDTO.version = badge.getVersion();
        badgeDTO.name = badge.getName();
        badgeDTO.tag = badge.getTag();
        badgeDTO.levels = new ArrayList<>();
        //ebben BadgeLevelek vannak, ezt kellene BadgelevelDTo-vá alakítni?
        List<BadgeLevel> levels = badge.getLevels();
        for (BadgeLevel item : levels) {
            badgeDTO.levels.add(badgeLevelService.getDTOfromBadgeLevel(item));
        }


        return badgeDTO;
    }

    //3. method used by the 3. solution works with the endpoint "/testbadgedtossimple"

    public List<BadgeDTO> getDTOListFromBadge() {
        List<BadgeDTO> badgeDTOs = new ArrayList<>();
        List<Badge> badges = badgeRepository.findAll();
        for (Badge badge : badges) {
            badgeDTOs.add(getDTOfromBadge(badge));
        }
        return badgeDTOs;
    }

    public Badge getBadgefromDTO(BadgeDTO badgeDTO) {
        Badge badge = new Badge();
        badge.setVersion(badgeDTO.getVersion());
        badge.setName(badgeDTO.getName());
        badge.setTag(badgeDTO.getTag());
        badge.setLevels(new ArrayList<>());
        //badgeRepository.save(badge);
        return badge;
    }


    public void createBadge(Badge badge) {
        badgeRepository.save(badge);
    }


    public BadgeSetDTO getAll() {
        BadgeSetDTO badgeSetDTO = new BadgeSetDTO(badgeRepository.findAll());
        return badgeSetDTO;
    }
}
