package com.greenfox.lvlup.service;

import com.greenfox.lvlup.model.dto.BadgeDTO;
import com.greenfox.lvlup.model.entity.Badge;
import com.greenfox.lvlup.repositrory.BadgeLevelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BadgeService {
    private BadgeLevelRepository badgeLevelRepository;
    private ModelMapper modelMapper;

    @Autowired
    public BadgeService(BadgeLevelRepository badgeLevelRepository, ModelMapper modelMapper) {
        this.badgeLevelRepository = badgeLevelRepository;
        this.modelMapper = modelMapper;
    }

    public List<BadgeDTO> convertBadgeToBadgeDTO() {
        List<BadgeDTO> badgeDTOs = new ArrayList<>();
        List<Badge> badges = badgeLevelRepository.findAll();
        for (Badge badge : badges) {
            BadgeDTO badgeDTO = modelMapper.map(badge, BadgeDTO.class);
            badgeDTOs.add(badgeDTO);
        }
        return badgeDTOs;
    }
}
