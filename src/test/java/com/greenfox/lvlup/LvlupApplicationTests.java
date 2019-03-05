package com.greenfox.lvlup;

import com.greenfox.lvlup.config.JwtSecurityConfig;
import com.greenfox.lvlup.config.OauthSecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration(exclude = {JwtSecurityConfig.class,
    OauthSecurityConfig.class,
    SecurityAutoConfiguration.class})
@ActiveProfiles("test")
public class LvlupApplicationTests {

  @Test
  public void contextLoads() {
  }
}

