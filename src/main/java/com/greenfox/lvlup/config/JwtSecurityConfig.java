package com.greenfox.lvlup.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@SpringBootApplication
@EnableWebSecurity
@Configuration
public class JwtSecurityConfig {

    @Order(1)
    public static class WebSecurityWithGoogle extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/auth").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .oauth2Login()
                    .authorizationEndpoint()
                    .baseUri("/auth")
                    .and()
                    .redirectionEndpoint()
                    .baseUri("/auth");
        }
    }

    public static class WebSecurityForAllEndpoints extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/**")
                    .authenticated();
        }
    }
}