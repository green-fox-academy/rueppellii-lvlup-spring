package com.greenfox.lvlup.service;

import com.greenfox.lvlup.model.dto.BadgeDTO;
import com.greenfox.lvlup.model.entity.Badge;
import org.hibernate.mapping.Bag;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BadgeService {
    BadgeDTO getDTOfromBadge(Badge badge);

    List<BadgeDTO> getDTOListFromEntities();

    Badge getBadgefromDTO (BadgeDTO badgeDTO);

    void createBadge (Badge badge);
}
