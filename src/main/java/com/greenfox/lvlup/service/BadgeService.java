package com.greenfox.lvlup.service;

import com.greenfox.lvlup.model.entityTestingDto.BadgeSetDTO;
import com.greenfox.lvlup.model.entityTestingDto.BadgeDTO;
import com.greenfox.lvlup.model.entity.Badge;

import java.util.List;

public interface BadgeService {
    BadgeDTO getDTOfromBadge(Badge badge);

    List<BadgeDTO> getDTOListFromBadge();

    Badge getBadgefromDTO (BadgeDTO badgeDTO);

    void createBadge (Badge badge);

    BadgeSetDTO getAll();
}
