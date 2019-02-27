package com.greenfox.lvlup.security;

import com.greenfox.lvlup.exception.CustomAuthenticationException;
import com.greenfox.lvlup.exception.DefaultExceptionHandler;
import com.greenfox.lvlup.exception.GeneralException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

  @Autowired
  JwtValidator validator;

  @Autowired
  DefaultExceptionHandler exceptionHandler;

  @Override
  protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                UsernamePasswordAuthenticationToken authentication)
      throws AuthenticationException {
  }

  @Override
  protected UserDetails retrieveUser(String username,
                                     UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken)
      throws AuthenticationException {
    JwtAccessToken jwtToken = (JwtAccessToken) usernamePasswordAuthenticationToken;

    String token = jwtToken.getToken();

    System.out.println(token);
    JwtUserDTO jwtUser = validator.validate(token);

    if (jwtUser == null) {
      throw new RuntimeException("JWT token is incorrect");
    }

    List<GrantedAuthority> grantedAuthorities = AuthorityUtils
        .commaSeparatedStringToAuthorityList(jwtUser.getRole());

    return new JwtUserPrinciple(jwtUser.getUsername(), token, jwtUser.getId(), grantedAuthorities);
  }


  @Override
  public boolean supports(Class<?> aClass) {
    return JwtAccessToken.class.isAssignableFrom(aClass);
  }
}
