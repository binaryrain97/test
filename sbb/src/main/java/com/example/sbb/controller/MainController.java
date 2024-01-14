package com.example.sbb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String index() {
        return "redirect:/article/list";
    }

    @GetMapping("/home")
    public String home() { return "home"; }
}
