package com.greenfox.lvlup.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthController {

    @RequestMapping("/auth")
    @ResponseBody
    public String something() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(auth.getPrincipal());
        return "index";
        }

    @RequestMapping("/authed")
    public String callback() {
        System.out.println("redirecting to home page");
        return "authed.html";
    }
}


