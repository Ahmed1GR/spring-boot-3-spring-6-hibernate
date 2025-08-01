package com.luv2code.springboot.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HelloWorldController {

    // new a controller method to show the initial HTML form
    @GetMapping("/showform")
    public String showForm(){
        return "helloworld-form";
    }
    @RequestMapping("/processForm")
    public String processForm(){
        return "helloworld";
    }

    @RequestMapping("/processFormVersionTwo")
    public String letsShoutDude (HttpServletRequest request , Model model ){

        // read the request parameter from the HTML form
        String theName = request.getParameter("studentName");

        // convert thw data to all caps
        theName = theName.toUpperCase();

        // create the message
        String result = "Yo " + theName;

        // add message to the model
        model.addAttribute("message", result);

        return "helloworld";
    }

        @PostMapping("/processFormVersionThree")
    public String processFormVersionThree(@RequestParam("studentName") String theName , Model model ){

        // convert thw data to all caps
        theName = theName.toUpperCase();

        // create the message
        String result = "Hey My Firnd from v3! " + theName;

        // add message to the model
        model.addAttribute("message", result);

        return "helloworld";
    }

}
