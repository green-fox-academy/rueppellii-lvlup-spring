package com.greenfox.lvlup.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {

  @Value("${SECRET}")
  private String secret;

  public JwtUserDTO validate(String token) {

    JwtUserDTO jwtUser = null;
    try {
      Claims body = Jwts.parser()
          .setSigningKey(secret)
          .parseClaimsJws(token)
          .getBody();

      jwtUser = new JwtUserDTO();

      jwtUser.setUsername((String) body.get("username"));
      jwtUser.setId((Long.parseLong((String )body.get("userId"))));
      jwtUser.setRole((String) body.get("role"));
    }
    catch (Exception e) {
      System.out.println(e);
    }
    return jwtUser;
  }
}
