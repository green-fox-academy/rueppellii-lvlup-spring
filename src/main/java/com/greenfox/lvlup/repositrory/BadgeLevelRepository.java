package com.greenfox.lvlup.repositrory;

import com.greenfox.lvlup.model.entity.Badge;
import com.greenfox.lvlup.model.entity.BadgeLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgeLevelRepository extends JpaRepository<BadgeLevel, Long> {
  List<BadgeLevel> findAll();
  BadgeLevel findBadgeLevelByLevelAndBadge(int oldLvl, Badge badge);
}
