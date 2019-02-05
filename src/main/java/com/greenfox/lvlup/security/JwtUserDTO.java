package com.greenfox.lvlup.security;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;

@Getter
@Setter
public class JwtUserDTO {
  private String username;
  @GeneratedValue
  private Long id;
  private String role;

  public JwtUserDTO(){
    this.username = "testuser";
    this.role = "testrole";
  }

}
