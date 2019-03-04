package com.greenfox.lvlup.repositrory;

import com.greenfox.lvlup.model.entity.Badge;
import com.greenfox.lvlup.model.entity.BadgeLevel;
import com.greenfox.lvlup.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.List;

@Repository
public interface BadgeLevelRepository extends CrudRepository<BadgeLevel, Id> {

  List<BadgeLevel> findAllByHoldersContaining(User user);

  List<BadgeLevel> findAll();

  BadgeLevel findBadgeLevelByLevelAndBadge(int oldLvl, Badge badge);
}
