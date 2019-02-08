package com.greenfox.lvlup.repositrory;

import com.greenfox.lvlup.model.entity.BadgeLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<BadgeLevel, Long> {
}
