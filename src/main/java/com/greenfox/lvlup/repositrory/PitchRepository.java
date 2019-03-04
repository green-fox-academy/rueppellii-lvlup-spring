package com.greenfox.lvlup.repositrory;

import com.greenfox.lvlup.model.entity.Pitch;
import com.greenfox.lvlup.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PitchRepository extends JpaRepository<Pitch, Long> {
  @Query(
      value = "SELECT * FROM Pitch p WHERE p.user_id = ?1",
      nativeQuery = true)
  List<Pitch> findPitchesByUserId(long id);
<<<<<<< HEAD
=======
  Pitch save(Pitch pitch);
>>>>>>> 8e7fb9d2a269c5f81215e683c9aad057086c29ad
}
