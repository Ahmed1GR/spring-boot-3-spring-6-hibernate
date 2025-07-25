package com.luv2code.cruddemo;


// import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.luv2code.cruddemo.dao.StudentDAO;
// import com.luv2code.cruddemo.entity.Student;
import com.luv2code.cruddemo.entity.Student;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {SpringApplication.run(CruddemoApplication.class, args);	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			// createStudent(studentDAO);

			creatMultipleStudents(studentDAO);

			// readStudent(studentDAO);

			// queryForStudents(studentDAO);

			// queryForStudentsByLastName(studentDAO);

			// updateStudent(studentDAO);

			// deleteStudent(studentDAO);

			// deleteAllStudents(studentDAO);
	};
}


	private void creatMultipleStudents(StudentDAO studentDAO) {
		// create multiple student objects
		System.out.println("Creating new student objects...");
		Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
		Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");
		Student tempStudent3 = new Student("Bonita", "Applebum", "bonita@luv2code.com");

		// save the student objects
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent1);	
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);	

		// display the id of the saved students
		System.out.println("Saved students. Generated ids: " + 
			tempStudent1.getId() + ", " + 
			tempStudent2.getId() + ", " + 
			tempStudent3.getId());
		System.out.println("Done!");
	}

	
/* 
	private void deleteAllStudents(StudentDAO studentDAO) {
		// delete all students
		System.out.println("Deleting all students...");
		int numRowsDeleted = studentDAO.deleteAll();
		System.out.println("Deleted rows: " + numRowsDeleted);
		System.out.println("Done!");

	}

	private void deleteStudent(StudentDAO studentDAO) {
		// delete student with id: ?
		int studentId = 3;
		System.out.println("Deleting student with id: " + studentId);
		studentDAO.delete(studentId);
		System.out.println("Deleted student with id: " + studentId);
		System.out.println("Done!");
	}


	private void updateStudent(StudentDAO studentDAO) {

		// retrieve student based on the id: primary key
		int studentId =1;
		System.out.println("Retrieving student with id: " + studentId);
		Student myStudent = studentDAO.findById(studentId);

		// change first name to "Scooby"
		System.out.println("Updating student...");
		myStudent.setFirstName("John");

		//update the student
		studentDAO.update(myStudent);

		// display the updated student
		System.out.println("Updated student: " + myStudent);
		System.out.println("Done!");
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		// get a list of students
		List<Student> theStudents = studentDAO.findByLastName("Doe");




		// display list of students
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		
		// get a list of students
		List<Student> theStudents = studentDAO.findAll();


		// disblay the students
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}


	private void readStudent(StudentDAO studentDAO) {
		// create a student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Daffy", "Duck", "daffy@luv2code.com");

		// save the student
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		// display id of the saved student
		int theId = tempStudent.getId();
		System.out.println("Saved student. Generated id: " + theId);

		// retrieve student based on the id: primary key
		System.out.println("Retrieving student with id: " + theId);
		Student myStudent = studentDAO.findById(theId);

		// display the student
		System.out.println("Found student: " + myStudent);

		

	}


	private void createStudent(StudentDAO studentDAO) {
		// create the student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Paul", "Doe", "paul@luv2code.com");
		
		// save the student object
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		// display the id of the saved student
		System.out.println("Saved student. Generated id: " + tempStudent.getId());
		System.out.println("Done!");

	}*/
}
