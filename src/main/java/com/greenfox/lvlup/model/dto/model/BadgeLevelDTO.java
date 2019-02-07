package com.greenfox.lvlup.model.dto.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class BadgeLevelDTO {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  public BadgeLevelDTO() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
