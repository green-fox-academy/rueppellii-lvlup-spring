package com.greenfox.lvlup.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.web.access.ExceptionTranslationFilter;

@Configuration
@EnableOAuth2Sso
@Order(1)
public class OauthSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private OAuth2ClientContextFilter oAuth2ClientContextFilter;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .cors()
        .and()
        .csrf()
        .disable()
        .authorizeRequests()
        .antMatchers("/login")
        .authenticated()
        .and()
        .formLogin()
        .and()
        .addFilterAfter(oAuth2ClientContextFilter, ExceptionTranslationFilter.class)
        .logout();
  }

  @Bean
  public FilterRegistrationBean oauth2ClientFilterRegistration1(
      OAuth2ClientContextFilter filter) {
    FilterRegistrationBean registration = new FilterRegistrationBean();
    registration.setFilter(filter);
    registration.setOrder(-100);
    return registration;
  }
}
