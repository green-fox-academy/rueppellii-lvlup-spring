package com.greenfox.lvlup.repositrory;

import com.greenfox.lvlup.model.entity.BadgeLevel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadgeLevelRepository extends CrudRepository<BadgeLevel, Long> {
}
