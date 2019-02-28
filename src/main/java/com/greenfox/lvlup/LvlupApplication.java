package com.greenfox.lvlup;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LvlupApplication implements CommandLineRunner {

  @Value("${spring.datasource.url}")
  private String dbname;

  public static void main(String[] args) {
    SpringApplication.run(LvlupApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println("****************************" + dbname);

  }
}


