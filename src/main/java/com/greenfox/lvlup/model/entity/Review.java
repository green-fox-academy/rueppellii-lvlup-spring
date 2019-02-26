package com.greenfox.lvlup.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String message;
  private boolean pitchStatus;
  @ManyToOne
  private User user;
  @ManyToOne (cascade={ALL})
  private Pitch pitch;

  public Review(String message, boolean pitchStatus) {
    this.message = message;
    this.pitchStatus = pitchStatus;
  }

  public Review(String message, boolean pitchStatus, User user) {
    this.message = message;
    this.pitchStatus = pitchStatus;
    this.user = user;
  }

  public Review(boolean pitchStatus, User user) {
    this.pitchStatus = pitchStatus;
    this.user = user;
  }
}
