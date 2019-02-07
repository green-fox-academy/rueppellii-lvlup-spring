package com.greenfox.lvlup.model.dto;
<<<<<<< HEAD

public class BadgeLevelDTO {

  private Long id;

  public BadgeLevelDTO() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
=======
import java.util.Set;

public class BadgeLevelDTO {
    public int level;
    public String description;
    public Set<UserDTO> holders;


>>>>>>> Dev
}
