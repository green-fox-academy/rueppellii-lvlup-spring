package com.greenfox.lvlup.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pitch {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private Date created;
  private int pitchedLevel;
  private String pitchedMessage;
  @ManyToOne
  private User user;
  @ManyToOne
  private BadgeLevel level;
  @ManyToOne
  private Badge badge;
  @OneToMany (mappedBy = "pitch")
  private List<Review> reviews;
}
