//
package com.luv2code.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class BaseballCoach implements Coach{
    public BaseballCoach() {
        System.out.println("BaseballCoach: inside default constructor" + getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() { return "Spend 30 minutes on batting practice"; }
}