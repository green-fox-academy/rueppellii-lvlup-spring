package com.greenfox.lvlup.model.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pitch {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  @CreationTimestamp
  private Date created;
  private int oldLevel;
  private int pitchedLevel;
  private String pitchedMessage;
  @ManyToOne
  private User user;
  @ManyToOne
  private BadgeLevel badgeLevel;
  @ManyToOne
  private Badge badge;
  @OneToMany (mappedBy = "pitch")
  private List<Review> reviews;

  public Pitch(Date created, int oldLevel, int pitchedLevel, String pitchedMessage, User user, Badge badge) {
    this.created = created;
    this.oldLevel = oldLevel;
    this.pitchedLevel = pitchedLevel;
    this.pitchedMessage = pitchedMessage;
    this.user = user;
    this.badge = badge;
  }
}
