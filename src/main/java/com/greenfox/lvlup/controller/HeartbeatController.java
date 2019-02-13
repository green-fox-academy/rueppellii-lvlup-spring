package com.greenfox.lvlup.controller;

import com.greenfox.lvlup.exception.GeneralException;
import com.greenfox.lvlup.exception.GeneralMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeartbeatController {
    @GetMapping(value = "/heartbeat",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getHeartbeat(@RequestHeader(value = "userTokenAuth", required = false) String token) throws Exception {
        if (token == null || token.equals("")) {
            throw new GeneralException("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(new GeneralMessage("Ok"), HttpStatus.OK);
    }
}
