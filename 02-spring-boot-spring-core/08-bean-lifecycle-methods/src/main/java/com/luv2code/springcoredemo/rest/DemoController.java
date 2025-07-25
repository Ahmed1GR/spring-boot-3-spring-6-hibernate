package com.luv2code.springcoredemo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springcoredemo.common.Coach;

@RestController
public class DemoController {
    private Coach myCoach;


@Autowired
public DemoController( 
    @Qualifier("cricketCoach") Coach theCoach ) {
        System.out.println("DemoController: inside constructor" + getClass().getSimpleName());
        myCoach = theCoach;
    }


    
@GetMapping("/dailyWorkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}