package com.greenfox.lvlup.model.mockdto;

import java.util.ArrayList;

public class UserBadgeSetDTO {
  private ArrayList<UserBadgeDTO> badges;

  private UserBadgeDTO test1 = new UserBadgeDTO("test badge 1", 2);
  private UserBadgeDTO test2 = new UserBadgeDTO("test badge 2", 1);

  public UserBadgeSetDTO(ArrayList<UserBadgeDTO> listOfBadges) {
    this.badges = listOfBadges;
  }

  public UserBadgeSetDTO() {
    badges = new ArrayList<>();
    badges.add(test1);
    badges.add(test2);
  }

  public ArrayList<UserBadgeDTO> getBadges() {
    return badges;
  }

  public void setBadges(ArrayList<UserBadgeDTO> badgeSetDTO) {
    this.badges = badgeSetDTO;
  }
}
