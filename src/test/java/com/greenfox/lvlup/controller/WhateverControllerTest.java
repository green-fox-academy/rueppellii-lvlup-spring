package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.model.dto.UserBadgeSetDTO;
import com.greenfox.lvlup.security.JwtAuthenticationProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.greenfox.lvlup.util.Converter.stringify;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BadgeController.class)
public class WhateverControllerTest {

//    @Autowired
//    private WebApplicationContext context;

//    @MockBean
//    private JwtAuthenticationProvider authenticationProvider;

    @Autowired
    private MockMvc mockMvc;
    UserBadgeSetDTO userBadgeSetDTO = new UserBadgeSetDTO();

//    @Before
//    public void setup() {
//        authenticationProvider = new JwtAuthenticationProvider();
//        mockMvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .apply(springSecurity())
//                .build();
//    }

    @Test
    public void whateverTest() throws Exception {
        mockMvc.perform(get("/whatever"))
                .andExpect(status().isOk())
                .andExpect(content().string(stringify(userBadgeSetDTO)));
    }
}
