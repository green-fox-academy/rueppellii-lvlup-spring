package com.greenfox.lvlup.service;

import com.greenfox.lvlup.model.dto.BadgeDTO;
import com.greenfox.lvlup.model.entity.Badge;
import com.greenfox.lvlup.model.entity.User;
import com.greenfox.lvlup.repositrory.BadgeRepository;
import com.greenfox.lvlup.repositrory.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BadgeService {
    private BadgeRepository badgeRepository;
    private ModelMapper modelMapper;
    private UserRepository userRepository;

    @Autowired
    public BadgeService(BadgeRepository badgeRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.badgeRepository = badgeRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public List<BadgeDTO> convertBadgeToBadgeDTO() {
        List<BadgeDTO> badgeDTOs = new ArrayList<>();
        List<Badge> badges = badgeRepository.findAll();
        for (Badge badge : badges) {
            BadgeDTO badgeDTO = modelMapper.map(badge, BadgeDTO.class);
            badgeDTOs.add(badgeDTO);
        }
        return badgeDTOs;
    }

    public Badge convertBadgeDTOToBadge(BadgeDTO badgeDTO) {
      if((badgeDTO.getLevels()== null) || badgeDTO.getLevels().equals("")) {
          Badge badgeToCreate = modelMapper.map(badgeDTO, Badge.class);
          return badgeToCreate;
      } else {
          //Badgelevels should be created too!!
           return null;
        }
    }

    public void createBadge (Badge badge, User user) {
        badge.setUser(user);
        user.setCreatedBadges((List<Badge>) badge);
        badgeRepository.save(badge);
        userRepository.save(user);
    }
}
