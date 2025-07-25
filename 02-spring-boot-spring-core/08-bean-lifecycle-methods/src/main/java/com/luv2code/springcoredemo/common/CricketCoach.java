//
package com.luv2code.springcoredemo.common;


import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class CricketCoach  implements Coach {
 
    public CricketCoach() {
     System.out.println("CricketCoach: inside default constructor" + getClass().getSimpleName());
     
 }

    // define my init method
    @PostConstruct
    public void doMyStartupStuff() {
        System.out.println("CricketCoach: inside myInitMethod" + getClass().getSimpleName());
    }
    // define my destroy method
    @PreDestroy
    public void doMyCleanupStuff() {
        System.out.println("CricketCoach: inside myCleanupMethod" + getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes :-)";
    }
}