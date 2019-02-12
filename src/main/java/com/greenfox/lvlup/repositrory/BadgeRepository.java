package com.greenfox.lvlup.repositrory;

import com.greenfox.lvlup.model.entity.Badge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgeRepository extends CrudRepository <Badge, Long> {
  List<Badge> findAll();
}
