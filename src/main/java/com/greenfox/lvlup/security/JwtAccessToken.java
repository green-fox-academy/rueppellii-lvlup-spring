package com.greenfox.lvlup.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@Setter
public class JwtAccessToken extends UsernamePasswordAuthenticationToken {
  String token;

  public JwtAccessToken(String token) {
    super(null, null);
    this.token = token;
  }
}

