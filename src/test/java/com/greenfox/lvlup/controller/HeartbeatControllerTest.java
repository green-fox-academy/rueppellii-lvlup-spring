package com.greenfox.lvlup.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(HeartbeatController.class)
public class HeartbeatControllerTest {
    String token = "TestToken123";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getHeartbeatValidRequestReturns200Ok() throws Exception {
        this.mockMvc.perform(get("/heartbeat")
                .header("userTokenAuth", token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Ok"));
    }

    @Test
    public void getHeartbeatNotContainingTokenValueReturns401Unauthorized() throws Exception {
        this.mockMvc.perform(get("/heartbeat")
                .header("userTokenAuth", ""))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("Unauthorized"));
    }

    @Test
    public void getHeartbeatNotContainingTokenReturns401Unauthorized() throws Exception {
        this.mockMvc.perform(get("/heartbeat"))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("Unauthorized"));
    }
}




