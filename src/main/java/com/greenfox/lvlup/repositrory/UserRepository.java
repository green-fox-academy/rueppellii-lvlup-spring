package com.greenfox.lvlup.repositrory;

import com.greenfox.lvlup.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

  User findUserByName(String name);

}
