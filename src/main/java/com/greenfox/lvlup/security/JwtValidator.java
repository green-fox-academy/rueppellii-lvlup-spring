package com.greenfox.lvlup.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class JwtValidator {

  //@Value("${SECRET}")
  private final String SECRET = "secret";

  public JwtUserDTO validate(String token) {

    JwtUserDTO jwtUser = null;
    try {
      Claims body = Jwts.parser()
          .setSigningKey(SECRET.getBytes("UTF-8"))
          .parseClaimsJws(token)
          .getBody();

      jwtUser = new JwtUserDTO();

      jwtUser.setUsername((String)body.get("username"));
      //jwtUser.setId(Long.parseLong((String) body.get("id")));
      jwtUser.setRole((String) body.get("role"));
    }
    catch (Exception e) {
      System.out.println(e);
    }
    return jwtUser;
  }
}
