package com.team33.evotingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginRegisterController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
