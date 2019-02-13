package com.greenfox.lvlup.repositrory;

import com.greenfox.lvlup.model.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {
  List<Badge> findAll();
}
