package com.greenfox.lvlup.repositrory;

import com.greenfox.lvlup.model.entity.Badge;
import com.greenfox.lvlup.model.entity.BadgeLevel;
import com.greenfox.lvlup.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import javax.persistence.Id;
import java.util.List;
import java.util.Set;

@Repository
public interface BadgeLevelRepository extends CrudRepository<BadgeLevel, Id> {

  List<BadgeLevel> findAllByHoldersContaining(User user);

  List<BadgeLevel> findAll();
=======
import java.util.List;

@Repository
public interface BadgeLevelRepository extends JpaRepository<BadgeLevel, Long> {
  List<BadgeLevel> findAll();
  BadgeLevel findBadgeLevelByLevelAndBadge(int oldLvl, Badge badge);
>>>>>>> 8e7fb9d2a269c5f81215e683c9aad057086c29ad
}
