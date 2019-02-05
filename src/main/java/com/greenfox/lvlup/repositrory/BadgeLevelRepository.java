package com.greenfox.lvlup.repositrory;

import com.greenfox.lvlup.model.entity.Badge;
import com.greenfox.lvlup.model.entity.BadgeLevel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgeLevelRepository extends CrudRepository<BadgeLevel, Long> {
    List<BadgeLevel> findAllByBadge(Badge badge);
}
