package com.luv2code.cruddemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {SpringApplication.run(CruddemoApplication.class, args);}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
		//	createSampleData(appDAO);

		// findInstructorById(appDAO);

		deleteInstructor(appDAO);
		};
	}

	private void deleteInstructor(AppDAO appDAO) {
		// delete the instructor with id 2
		int theId = 1;
		System.out.println("Deleting instructor with id: " + theId);
		appDAO.deleteInstructorById(theId);

		System.out.println("Deleted instructor with id: " + theId);	
	}

	private void findInstructorById(AppDAO appDAO) {
		// find the instructor by id
		int theId = 2;
		System.out.println("Finding instructor with id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("Found instructor: " + tempInstructor);

		if (tempInstructor != null) {
			System.out.println("Associated details: " + tempInstructor.getInstructorDetail());
		} else {
			System.out.println("No instructor found with id: " + theId);
		}
	}

	private void createSampleData(AppDAO appDAO) {
		/* 
		// Create the instructor
		Instructor tempInstructor = new Instructor("Chad", "Darby", "darby@luv2code.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail("luv2code.com/youtube", "luv2code");

		*/

		// Create the instructor
		Instructor tempInstructor = new Instructor("Madhu", "Patel", "madhu@luv2code.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail("luv2code.com/youtube", "Guitar");

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// save the instructor
		//
		// Note: this will also save the details object
		// because of CascadeType.ALL
		//
		System.out.println("Saving instructor: " + tempInstructor);
		appDAO.save(tempInstructor);



		System.out.println("Done!");
	
	}
}
