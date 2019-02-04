package com.greenfox.lvlup.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@Setter
//public class JwtAccessToken extends Jwt
public class JwtAccessToken extends UsernamePasswordAuthenticationToken {
  String token;

  public JwtAccessToken(String token) {
    super(null, null);
    this.token = token;
  }
}

