package com.team33.evotingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ElectoralController {

    @GetMapping("/electoral")
    public String electoralPage() {
        return "electoral";
    }
}
