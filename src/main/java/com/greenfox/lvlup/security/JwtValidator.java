package com.greenfox.lvlup.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {

  private String secret = "secret";

  public JwtUserDTO validate(String token) {

    JwtUserDTO jwtUser = null;
    try {
      Claims body = Jwts.parser()
          .setSigningKey(secret)
          .parseClaimsJws(token)
          .getBody();
      System.out.println(body);

      jwtUser = new JwtUserDTO();

      jwtUser.setUsername(body.getSubject());
      jwtUser.setId(Long.parseLong((String) body.get("userId")));
      jwtUser.setRole((String) body.get("role"));
    }
    catch (Exception e) {
      System.out.println(e);
    }

    return jwtUser;
  }
}
