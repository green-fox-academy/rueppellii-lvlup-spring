package com.greenfox.lvlup.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
  @ManyToOne
  private Pitch pitch;
}
