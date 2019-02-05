package com.greenfox.lvlup.model.entityTestingDto;

import com.greenfox.lvlup.model.entity.Badge;

import java.util.List;

public class BadgeSetDTO {
    List<Badge> badgesToReturn;

    public BadgeSetDTO() {
    }

    public BadgeSetDTO(List<Badge> badgesToReturn) {
        this.badgesToReturn = badgesToReturn;
    }

    public List<Badge> getBadgesToReturn() {
        return badgesToReturn;
    }

    public void setBadgesToReturn(List<Badge> badgesToReturn) {
        this.badgesToReturn = badgesToReturn;
    }
}
