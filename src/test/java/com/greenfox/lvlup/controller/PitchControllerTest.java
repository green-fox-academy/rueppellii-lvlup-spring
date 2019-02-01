//package com.greenfox.lvlup.controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.greenfox.lvlup.model.PitchSetDTO;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(PitchController.class)
//public class PitchControllerTest {
//
//  @Autowired
//  MockMvc mockMvc;
//
//  String token = "testToken";
//
//  PitchSetDTO pitchSetDTO = new PitchSetDTO();
//
//  @Test
//  public void getPitchesWithCorrectHeader() throws Exception {
//    mockMvc.perform(get("/pitches")
//        .header("userTokenAuth", token)
//        .contentType(MediaType.APPLICATION_JSON))
//        .andExpect(status().isOk())
//        .andExpect(content().string(stringify(pitchSetDTO)))
//        .andReturn();
//  }
//
//  @Test
//  public void checkIfTokenIsProvided() throws Exception {
//    mockMvc.perform(get("/pitches")
//        .contentType(MediaType.APPLICATION_JSON))
//        .andExpect(status().isUnauthorized())
//        .andExpect(jsonPath("$.error").value("unauthorized"));
//  }
//
//  public static String stringify(Object object) throws JsonProcessingException {
//    return new ObjectMapper().writeValueAsString(object);
//  }
//}
