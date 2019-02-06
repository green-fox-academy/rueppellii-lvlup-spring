package com.greenfox.lvlup.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Setter
public class JwtUserPrinciple implements UserDetails {

  private String username;
  private String token;
  private Long id;
  private Collection<? extends GrantedAuthority> authorities;

  public JwtUserPrinciple() {}

  public JwtUserPrinciple(String username, String token, Long id, Collection<? extends GrantedAuthority> authorities) {
    this.username = username;
    this.token = token;
    this.id = id;
    this.authorities = authorities;
  }

  @Override
  public String toString() {
    return super.toString();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
