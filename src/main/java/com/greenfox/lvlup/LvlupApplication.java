package com.greenfox.lvlup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@SpringBootApplication
@EnableOAuth2Sso
public class LvlupApplication {

  public static void main(String[] args) {
    SpringApplication.run(LvlupApplication.class, args);
  }

}

