package com.luv2code.aopdemo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;
import com.luv2code.aopdemo.service.TrafficFortuneService;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO,
	                                             MembershipDAO theMembershipDAO
	                                             , TrafficFortuneService theTrafficFortuneService) {
		return runner -> {
			// This is where you can add any startup logic if needed
			// demoTheBeforeAdvice(theAccountDAO, theMembershipDAO);	
			// demoTheAfterReturningAdvice(theAccountDAO);
			// demoTheAfterThrowingAdvice(theAccountDAO);
			// demoTheAfterAdvice(theAccountDAO);
			// demoTheAroundAdvice(theTrafficFortuneService);
			// demoTheAroundAdviceHandleExeception(theTrafficFortuneService);
			demoTheAroundAdviceRethrowException(theTrafficFortuneService);
		};
	}

	private void demoTheAroundAdviceRethrowException(TrafficFortuneService theTrafficFortuneService) {

		System.out.println("\nMain Program: demoTheAroundAdviceRethrowException");
		System.out.println("Calling getFortune()");

		boolean tripWire = true;
		String data = theTrafficFortuneService.getFortune(tripWire);

		System.out.println("\nMy fortune is: " + data);
		System.out.println("Finished!\n");
		
	}

	private void demoTheAroundAdviceHandleExeception(TrafficFortuneService theTrafficFortuneService) {

		System.out.println("\nMain Program: demoTheAroundAdviceHandleExeception");
		System.out.println("----");

		boolean tripWire = true;
		String data = theTrafficFortuneService.getFortune(tripWire);

		System.out.println("\nMy fortune is: " + data);
		System.out.println("Finished!\n");
		
	}

	private void demoTheAroundAdvice(TrafficFortuneService theTrafficFortuneService) {
		
		System.out.println("\nMain Program: demoTheAroundAdvice");
		System.out.println("----");

		// call the business method
		String data = theTrafficFortuneService.getFortune();

		System.out.println("\nMy fortune is: " + data);
		System.out.println("Finished!\n");
		/*
		// Simulate a delay
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} */
	}

	private void demoTheAfterAdvice(AccountDAO theAccountDAO) {
		
		// call method to find the account
		List<Account> theAccounts = null;
		
		try{
			// add a boolean flag to simulate an exception
			boolean tripWire = false;
		theAccounts = theAccountDAO.findAccounts(tripWire);
		} catch (Exception exc) {
			System.out.println("\n\nMain Program: ... caught exception :"+ exc);
		}
		// display the accounts
		System.out.println("\n\nMain Program: demoTheAfterThrowingAdvice");
		System.out.println("----");
		System.out.println(theAccounts);
		System.out.println("\n");
		System.out.println("Finished!");			
	}

	private void demoTheAfterThrowingAdvice(AccountDAO theAccountDAO) {
		
		// call method to find the account
		List<Account> theAccounts = null;
		
		try{
			// add a boolean flag to simulate an exception
			boolean tripWire = true;
		theAccounts = theAccountDAO.findAccounts(tripWire);
		} catch (Exception exc) {
			System.out.println("\n\nMain Program: ... caught exception :"+ exc);
		}
		// display the accounts
		System.out.println("\n\nMain Program: demoTheAfterThrowingAdvice");
		System.out.println("----");
		System.out.println(theAccounts);
		System.out.println("\n");
		System.out.println("Finished!");		
	}

	private void demoTheAfterReturningAdvice(AccountDAO theAccountDAO) {
		
		// call method to find the account
		List<Account> theAccounts = theAccountDAO.findAccounts();

		// display the accounts
		System.out.println("\n\nMain Program: demoTheAfterReturningAdvice");
		System.out.println("----");

		// display the accounts
		System.out.println(theAccounts);
		System.out.println("\n");
		System.out.println("Finished!");
	}

	private void demoTheBeforeAdvice(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO) {

		// call the business method
		Account myAccount = new Account();
		myAccount.setName("Madhu");
		myAccount.setLevel("Platinum");
		
		theAccountDAO.addAccount(myAccount, true);	
		theAccountDAO.doWork();

		// call the getter/setter methods
		theAccountDAO.setName("foobar");
		theAccountDAO.setServiceCode("Silver");

		String name = theAccountDAO.getName();
		String serviceCode = theAccountDAO.getServiceCode();

		// call the mempership business method
		theMembershipDAO.addSillyMember();
		theMembershipDAO.goToSleep();
	}
}
