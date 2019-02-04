package com.greenfox.lvlup.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtUserDTO {
  private String username;
  private Long id;
  private String role;

}
