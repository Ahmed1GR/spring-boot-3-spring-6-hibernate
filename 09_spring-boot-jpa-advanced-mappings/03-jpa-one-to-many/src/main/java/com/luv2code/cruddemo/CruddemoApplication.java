package com.luv2code.cruddemo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {SpringApplication.run(CruddemoApplication.class, args);}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
		// createSampleData(appDAO);

		// findInstructorById(appDAO);

		// deleteInstructor(appDAO);

		// findInstructorDetail(appDAO);

		// deleteInstructorDetail(appDAO);

		// createInstructorWithCourses(appDAO);

		// findInstructorWithCourses(appDAO);

		// findCoursesForInstructor(appDAO);

		// findInstructorWithCoursesJoinFetch(appDAO);

		// updateInstructor(appDAO);

		// updateCourse(appDAO);

		deleteCourse(appDAO);
		};
	}

	private void deleteCourse(AppDAO appDAO) {

		int theId = 10;
		
		System.out.println("Deleting course id: " + theId);

		appDAO.deleteCourseById(theId);

		System.out.println("Done!!");
	}

	private void updateCourse(AppDAO appDAO) {

		int theId = 10;

		Course tempCourse = appDAO.findCourseById(theId);
		System.out.println("Updating course id: " + theId);

		tempCourse.setTitle("Enjoy the Simple Things");
		appDAO.update(tempCourse);

		System.out.println("Done!!");
	}

	private void updateInstructor(AppDAO appDAO) {
		// find the instructor by id
		int theId = 1;
		System.out.println("Finding instructor with id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("Instructor: " + tempInstructor);

		// set the instructor's name
		tempInstructor.setLastName("TESTER");

		// save the instructor
		System.out.println("Updating instructor: " + tempInstructor);
		appDAO.update(tempInstructor);
		System.out.println("Done");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		// find the instructor by id
		int theId = 1;
		System.out.println("Finding instructor with id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("Instructor: " + tempInstructor);
		System.out.println("Courses: " + tempInstructor.getCourses());

		System.out.println("Done");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor with id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("Instructor: " + tempInstructor);
		System.out.println("Courses: " + theId);
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);

		tempInstructor.setCourses(courses);

		System.out.println("Courses: " + tempInstructor.getCourses());
		System.out.println("Done");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		// find the instructor by id
		int theId = 1;
		System.out.println("Finding instructor with id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("Instructor: " + tempInstructor);
		System.out.println("Courses: " + tempInstructor.getCourses());

		System.out.println("Done");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		// create the instructor
		Instructor tempInstructor = new Instructor("Susan", "Public", "susan.public@luv2code.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com/",
				"Video Games !!!");

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// create some courses
		Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
		Course tempCourse2 = new Course("The Pinball Masterclass");

		// add courses to instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		// save the instructor
		//
		// Note: this will also save the details object
		// because of CascadeType.ALL
		//
		System.out.println("Saving instructor: " + tempInstructor);
		System.out.println("The associated courses are: " + tempInstructor.getCourses());
		appDAO.save(tempInstructor);

		System.out.println("Done!!");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		// delete the instructor detail with id 2
		int theId = 3;
		System.out.println("Deleting instructor detail with id: " + theId);
		appDAO.deleteInstructorDetailById(theId);

		System.out.println("Deleted instructor detail with id: " + theId);
		System.out.println("Done!!");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		// find the instructor detail by id
		int theId = 2;
		System.out.println("Finding instructor detail with id: " + theId);
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

		System.out.println("Found instructor detail: " + tempInstructorDetail);

		if (tempInstructorDetail != null) {
			System.out.println("Associated instructor: " + tempInstructorDetail.getInstructor());
		} else {
			System.out.println("No instructor detail found with id: " + theId);
		}
		System.out.println("Done!!");
	}

	private void deleteInstructor(AppDAO appDAO) {
		// delete the instructor with id 2
		int theId = 1;
		System.out.println("Deleting instructor with id: " + theId);
		appDAO.deleteInstructorById(theId);

		System.out.println("Deleted instructor with id: " + theId);	

		System.out.println("Done!!");
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
