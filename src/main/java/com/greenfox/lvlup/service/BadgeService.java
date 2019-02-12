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
        Badge badgeToCreate = modelMapper.map(badgeDTO, Badge.class);
        return badgeToCreate;

 /*     if((badgeDTO.getLevels().size()== 0) || badgeDTO.getLevels().equals("")) {
          Badge badgeToCreate = modelMapper.map(badgeDTO, Badge.class);
          return badgeToCreate;
      } else {
          //Badgelevels should be created too!!
           return null;
        }*/
    }

    public void createBadge(Badge badge, User user) {
        badge.setUser(user);
        badgeRepository.save(badge);
    }
}
/*
    public User findUserById(long id) throws GeneralException {
        Optional<User> user = repo.findById(id);
        if(user.isPresent()) {
            return user.get();
        }
        throw new GeneralException("User was not found.", HttpStatus.NOT_FOUND);
    }*/
