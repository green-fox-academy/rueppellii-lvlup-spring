package com.greenfox.lvlup.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Pitches {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "pitchesid")
  private long id;
  private Date timestamp;
}
