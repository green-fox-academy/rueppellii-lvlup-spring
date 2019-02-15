package com.greenfox.lvlup.service;

import com.greenfox.lvlup.exception.GeneralException;
import com.greenfox.lvlup.model.dto.library.BadgeDTO;
import com.greenfox.lvlup.model.entity.Badge;
import com.greenfox.lvlup.model.entity.User;
import com.greenfox.lvlup.repositrory.BadgeRepository;
import com.greenfox.lvlup.repositrory.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BadgeService {
    private BadgeRepository badgeRepository;
    private ModelMapper modelMapper;
    private UserService userService;

    @Autowired
    public BadgeService(BadgeRepository badgeRepository, ModelMapper modelMapper, UserService userService) {
        this.badgeRepository = badgeRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
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

    public void createBadge(BadgeDTO badgeDTO, long userId) throws GeneralException {
        Badge badgeToCreate = this.convertBadgeDTOToBadge(badgeDTO);
        User badgeCreatorUser = userService.findUserById(userId);
        this.saveBadgeIntoDatabase(badgeToCreate, badgeCreatorUser);
    }

    public Badge convertBadgeDTOToBadge(BadgeDTO badgeDTO) {
        Badge badgeToCreate = modelMapper.map(badgeDTO, Badge.class);
        return badgeToCreate;
    }

    public void saveBadgeIntoDatabase(Badge badge, User user) throws GeneralException {
        if (findBadgeByNameAndVersion(badge) == null) {
            badge.setUser(user);
            badgeRepository.save(badge);
        } else
            throw new GeneralException("Badge with this version already exists. Please modify version.", HttpStatus.BAD_REQUEST);
    }

    public Badge findBadgeByNameAndVersion(Badge badge) {
        Badge badgeExisting = badgeRepository.findBadgeByNameAndVersion(badge.getName(), badge.getVersion()).orElse(null);
        return badgeExisting;
    }
}
