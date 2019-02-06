package com.greenfox.lvlup.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
public class JwtGenerator {

 @Value("${SECRET}")
  private String SECRET;

  public String generate(JwtUserDTO jwtUser) {
    Claims claims = Jwts.claims()
        .setSubject(jwtUser.getUsername());
    claims.put("userId", String.valueOf(jwtUser.getId()));
    claims.put("role", jwtUser.getRole());

    return Jwts.builder()
        .setClaims(claims)
        .setExpiration(new Date(System.currentTimeMillis() + 500000))
        .signWith(SignatureAlgorithm.HS512, SECRET)
        .compact();
  }
}
