package com.luv2code.springboot.demosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
    @GetMapping("/")
    public String showHome(){
        return "home"; 
    }

    @GetMapping("/leaders")
    public String showLeaders(){
        return "leaders"; 
    }
    @GetMapping("/systems")
    public String showSystems(){
        return "systems"; 
    }

}
