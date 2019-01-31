package com.greenfox.lvlup.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping(value = "/auth")
    public String showIfAuthenticated() {
        return "Authenticated";
    }
}
