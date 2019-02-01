package com.greenfox.lvlup.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;

@Getter
@Setter
public class JwtAccessToken extends Jwt {
  String token;

  public JwtAccessToken(String token) {
    this.token = token;
  }
}

