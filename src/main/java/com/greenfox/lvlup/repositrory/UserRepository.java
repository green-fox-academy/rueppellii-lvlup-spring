package com.greenfox.lvlup.repositrory;

import com.greenfox.lvlup.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

  User findUserByName(String name);

  Optional<User> findByTokenAuth(String tokenAuth);
}
