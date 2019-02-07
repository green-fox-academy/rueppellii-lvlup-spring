package com.greenfox.lvlup.repository;

import com.greenfox.lvlup.model.Archetype;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchetypeRepository extends CrudRepository<Archetype, Long> {
}
