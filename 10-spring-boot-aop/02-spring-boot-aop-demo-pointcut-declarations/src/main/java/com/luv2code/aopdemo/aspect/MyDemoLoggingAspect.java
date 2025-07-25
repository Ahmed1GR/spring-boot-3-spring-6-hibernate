package com.luv2code.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Aspect
@Component
@Order(1) // This aspect has the highest priority
public class MyDemoLoggingAspect {

    // this is where we add all of our related advices for logging
/* 
    public Object afterGetFortune(
        ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
            try {
                // let's execute the method
                Object result = theProceedingJoinPoint.proceed();

                return result;
            } catch (Exception e) {
                // log the exception
                System.out.println("@Around advice: We have a problem " + e);

                // rethrow the exception
                throw e;
            }
        }*/

    // @Around advice is not used here, so we will not implement it
    @Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(
            ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {

                // print out mrthod we are advising on 
                String method = theProceedingJoinPoint.getSignature().toShortString();
                System.out.println("\n=====>>> Executing @Around advice on method: " + method);
 
                // get begin timestamp
                // Long begin = System.currentTimeMillis();
                Long begin = System.nanoTime();

                // now, let's execute the method
                Object result = null;
                try{
                result = theProceedingJoinPoint.proceed();
                } catch (Exception e) {

                    // log the exception
                    System.out.println(e.getMessage());

                    // rethrow the exception
                    throw e;
                }

                // get end timestamp
                // Long end = System.currentTimeMillis();
                Long end = System.nanoTime();

                // compute the duration and display it
                Long duration = end - begin;
                System.out.println("\n=====>>> Duration: " + duration + " nanoseconds");

                return result;


            }

    // add the @After Advice to the findAccounts method
    @After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {

        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @After (finally) advice on method: " + method);
    }

    // @ِAfterThrowing advice is used to handle exceptions
    @AfterThrowing(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "theExc")
    public void afterThrowingFindAccountAdvice(JoinPoint theJoinPoint, Throwable theExc) {
        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterThrowing on method: " + method);

        // log the exception
        System.out.println("=====>>> The exception is: " + theExc);
    }

    // let's start with an @AfterReturning advice

    @AfterReturning(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountAdvice(JoinPoint theJoinPoint, List<Account> result) {
        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterReturning on method: " + method);

        // print out the results of the method call
        System.out.println("======>>>Result: " + result);

        // let's post-process the data
        convertAccountNamesToUpperCase(result);
        System.out.println("\n======>>>Modified result: " + result);

    }

    private void convertAccountNamesToUpperCase(List<Account> result) {
        for (Account tempAccount : result) {
            // get the uppercase version of the name
            String theUpperName = tempAccount.getName().toUpperCase();

            // update the name on the account
            tempAccount.setName(theUpperName);
        }
    }

    // add more @Before advice methods here if needed
    @Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
        System.out.println("\n=====>>>1 Executing @Before advice on addAccount()");

        // display the method signature
        MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();
        System.out.println("Method: " + methodSignature);

        // display the method arguments
        Object[] args = theJoinPoint.getArgs();
        for (Object tempArg : args) {
            System.out.println("Argument: " + tempArg);

            if (tempArg instanceof Account){

                // downcast and print Account specific stuff
                Account theAccount = (Account) tempArg;
                // print Account properties
                System.out.println("Account name: " + theAccount.getName());
                System.out.println("Account level: " + theAccount.getLevel());
            }
        }

    }
}
