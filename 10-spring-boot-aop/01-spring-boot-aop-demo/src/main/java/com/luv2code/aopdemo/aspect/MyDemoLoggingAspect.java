package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    // this is where we add all of our related advices for logging

    // lets's start with an @Before advice

    @Before("execution(* com.luv2code.aopdemo.dao.*.*(..))")
    public void beforeAddAccountAdvice() {
        System.out.println("\n=====>>> Executing @Before advice on addAccount()");
    }

}
