package com.greenfox.lvlup.config;

import com.greenfox.lvlup.security.JwtAuthenticationEntryPoint;
import com.greenfox.lvlup.security.JwtAuthenticationProvider;
import com.greenfox.lvlup.security.JwtAuthenticationTokenFilter;
import com.greenfox.lvlup.security.JwtSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class TokenValidationConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private JwtAuthenticationProvider authenticationProvider;
  @Autowired
  private JwtAuthenticationEntryPoint entryPoint;

  @Bean
  public AuthenticationManager authenticationManager() {
    return new ProviderManager(Collections.singletonList(authenticationProvider));
  }

  @Bean
  public JwtAuthenticationTokenFilter authenticationTokenFilter() {
    JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();
    filter.setAuthenticationManager(authenticationManager());
    filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
    return filter;
  }

  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/")
        .authenticated()
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(entryPoint)
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    http.headers().cacheControl();
  }
}
