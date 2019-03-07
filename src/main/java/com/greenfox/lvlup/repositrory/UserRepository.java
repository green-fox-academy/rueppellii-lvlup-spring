package com.greenfox.lvlup.repositrory;

import com.greenfox.lvlup.model.entity.BadgeLevel;
import com.greenfox.lvlup.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

  User findUserByName(String name);
  //User findUserByBadgeLevel(BadgeLevel badgeLevel);

  Optional<User> findByTokenAuth(String tokenAuth);
  @Query(value= "SELECT u.* FROM users u join badgelevel_user bu on u.id= bu.user_id join badgelevels bl on bl.id=bu.badgelevel_id where u.id=?1 and bl.badge_id=?2 and bu.badgelevel_id=?3", nativeQuery=true)
  //@Query(value = "SELECT * FROM fox_order where status=?1", nativeQuery=true)
  User findUserBadgeWithOldLevel(long userId, long badgeId, long badgeLevelId);
}
