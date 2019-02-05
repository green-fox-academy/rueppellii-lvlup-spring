package com.greenfox.lvlup.service;
import com.greenfox.lvlup.model.entity.User;
import com.greenfox.lvlup.model.entityTestingDto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserDTO getDTOfromUser(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.name = user.getName();
        return userDTO;
    }
}
