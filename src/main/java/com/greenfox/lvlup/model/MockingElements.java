package com.greenfox.lvlup.model;

public class MockingElements {

  private String sucessful = "{" +
      "\"myPitches\": [ " +
        "{ " +
          "\"timestamp\": \"2018-11-29 17:10:47\"," +
          "\"username\": \"balazs.barna\", " +
          "\"badgeName\": \"Programming\"," +
          "\"oldLevel\": 2," +
          "\"pitchedLevel\": 3," +
          "\"pitchMessage\": \"I improved in React, Redux, basic JS, NodeJS, Express and in LowDB, pls give me more money\"," +
          "\"holders\": [ " +
            "{ " +
              "\"name\": \"sandor.vass\"," +
              "\"message\": null," +
              "\"pitchStatus\": false" +
            "}" +
          "] " +
        "}" +
      "]," +
      "\"pitchesToReview\": [" +
        "{" +
          "\"timestamp\": \"2018-11-29 17:10:47\"," +
          "\"username\": \"berei.daniel\", " +
          "\"badgeName\": \"English speaker\"," +
          "\"oldLevel\": 2," +
          "\"pitchedLevel\": 3," +
          "\"pitchMessage\": \"I was working abroad for six years, so I can speak english very well. Pls improve my badge level to 3.\", " +
          "\"holders\": [" +
            "{" +
              "\"name\": \"balazs.jozsef\"," +
              "\"message\": \"Yes, you are able to speak english\", " +
              "\"pitchStatus\": true" +
            "}" +
          "]" +
        "}" +
      "]" +
      "}";

  public String getSucessful() {
    return sucessful;
  }
}


