package com.greenfox.lvlup.service;

import com.greenfox.lvlup.model.dto.user.UserBadgeDTO;
import com.greenfox.lvlup.model.dto.user.UserDto;
import com.greenfox.lvlup.model.entity.User;
import java.util.Set;

public interface UserService {
  UserDto getUserDetailsById(long id);
  Set<UserBadgeDTO> getUserBadgeDTOs(User user);
}
