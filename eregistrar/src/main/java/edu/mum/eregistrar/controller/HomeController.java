package edu.mum.eregistrar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value = {"/eregistar", "/eregistar/home"})
    public String home() {
        return "home/index";
    }
}
