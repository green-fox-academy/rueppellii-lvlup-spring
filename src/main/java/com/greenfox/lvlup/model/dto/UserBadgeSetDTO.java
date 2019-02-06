package com.greenfox.lvlup.model.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UserBadgeSetDTO {
  private Set<UserBadgeDTO> badges;

//  private UserBadgeDTO test1 = new UserBadgeDTO("test badge 1", 2);
//  private UserBadgeDTO test2 = new UserBadgeDTO("test badge 2", 1);

  public UserBadgeSetDTO(Set<UserBadgeDTO> setOfBadges) {
    this.badges = setOfBadges;
  }

  public UserBadgeSetDTO() {
    badges = new HashSet<>();
//    badges.add(test1);
//    badges.add(test2);
  }

  public Set<UserBadgeDTO> getBadges() {
    return badges;
  }

  public void setBadges(Set<UserBadgeDTO> badgeSetDTO) {
    this.badges = badgeSetDTO;
  }
}
