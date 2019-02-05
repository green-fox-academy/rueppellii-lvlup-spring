package com.greenfox.lvlup.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greenfox.lvlup.model.entity.Badge;
import com.greenfox.lvlup.model.entity.BadgeLevel;
import com.greenfox.lvlup.model.entity.User;
import com.greenfox.lvlup.model.entityTestingDto.BadgeDTO;
import com.greenfox.lvlup.model.entityTestingDto.UserDTO;
import com.greenfox.lvlup.repositrory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO getDTOfromUser(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.name = user.getName();
        return userDTO;
    }

/*    public List<UserDTO> getDTOListFromUser() {
        List<UserDTO> userDTOS = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User item : users) {
            userDTOS.add(getDTOfromUser(item));
        }
        return userDTOS;
    }*/
}
