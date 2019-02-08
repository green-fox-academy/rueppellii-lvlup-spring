package com.greenfox.lvlup.repositrory;

import com.greenfox.lvlup.model.entity.Archetype;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchetypeRepository extends CrudRepository<Archetype, Long> {

  List<Archetype> findAll();
}
