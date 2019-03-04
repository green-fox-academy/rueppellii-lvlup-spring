package com.greenfox.lvlup.repositrory;

import com.greenfox.lvlup.model.entity.Badge;
import com.greenfox.lvlup.model.entity.BadgeLevel;
import com.greenfox.lvlup.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Set;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {

  List<Badge> findAll();

  Badge findBadgeByName(String name);

  Badge findBadgeByNameAndVersion (String name, String version);
}
