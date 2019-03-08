package com.greenfox.lvlup.repositrory;

import com.greenfox.lvlup.model.entity.Pitch;
import com.greenfox.lvlup.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PitchRepository extends JpaRepository<Pitch, Long> {
  List<Pitch> findPitchesByUserId(long id);
}
