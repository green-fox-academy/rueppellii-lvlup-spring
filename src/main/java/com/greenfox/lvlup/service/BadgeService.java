package com.greenfox.lvlup.service;

import com.greenfox.lvlup.exception.GeneralException;
import com.greenfox.lvlup.model.dto.library.BadgeDTO;
import com.greenfox.lvlup.model.entity.Badge;
import com.greenfox.lvlup.model.entity.User;
import com.greenfox.lvlup.repositrory.BadgeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

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

    public boolean createBadge(BadgeDTO badgeDTO, String tokenAuth) throws GeneralException {
        boolean isCreated = false;
        Badge badgeToCreate = this.convertBadgeDTOToBadge(badgeDTO);
        User badgeCreatorUser = userService.findUserByTokenAuth(tokenAuth);
        if (badgeCreatorUser != null) {
            this.saveBadgeIntoDatabase(badgeToCreate, badgeCreatorUser);
            isCreated = true;
        }
        return isCreated;
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
        Badge badgeExisting = badgeRepository.findBadgeByNameAndVersion(badge.getName(), badge.getVersion());
        return badgeExisting;
    }
}
