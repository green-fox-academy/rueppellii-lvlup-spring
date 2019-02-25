package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.mockdto.MockingElements;
import com.greenfox.lvlup.service.implementation.UserServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

public class UserControllerTest {
  @MockBean
  UserServiceImpl service;
  MockingElements elements = new MockingElements();
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void getUserDetails() {

  }
}
