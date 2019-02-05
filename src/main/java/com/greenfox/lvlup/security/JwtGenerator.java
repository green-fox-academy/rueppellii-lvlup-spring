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

 //@Value("${SECRET}")
  private final String SECRET = Base64.getEncoder().encodeToString("secret".getBytes());

  public String generate(JwtUserDTO jwtUser) {
    Claims claims = Jwts.claims()
        .setSubject(jwtUser.getUsername());
    claims.put("userId", String.valueOf(jwtUser.getId()));
    //String.valueOf(jwtUser.getId());
    claims.put("role", jwtUser.getRole());

    // this is the jwt token itself; secret code should be environmental variable
    return Jwts.builder()
        .setClaims(claims)
        .setExpiration(new Date(System.currentTimeMillis() + 30000))
        .signWith(SignatureAlgorithm.HS512, SECRET)
        .compact();

///another way to build a token and set claims
    /*String token = Jwts.builder().setSubject(loginRequest.getUserName())
        .setExpiration(new Date(System.currentTimeMillis() + 30000))
        .claim("Test Data", "Test Data").claim("ipAddress", IpAddress)
        .signWith(SignatureAlgorithm.HS512, secretKey)
        .compact();

  }*/
  }
}
