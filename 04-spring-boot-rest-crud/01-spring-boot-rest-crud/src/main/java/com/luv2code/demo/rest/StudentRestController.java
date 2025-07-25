package com.luv2code.demo.rest;

import java.util.ArrayList;
import java.util.List;

//
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.demo.entity.Student;

import jakarta.annotation.PostConstruct;




@RestController
@RequestMapping("/api")
public class StudentRestController {
    // add code to expose "/students" endpoint

    
private List<Student> theStudents;

@PostConstruct
public void loadData() {
    theStudents = new ArrayList<>();
        theStudents.add(new Student("Pool", "Parker"));
        theStudents.add(new Student("Mary", "Smith"));
        theStudents.add(new Student("Sara", "Wilson"));
}

    @GetMapping("/students")
    public List<Student> getStudents() {

        return theStudents;
    }

    // define endpoint for "/students/{studentId}" - return single student
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        // just for testing, return a dummy student
        // check the studentId against the list size

        if(studentId >= theStudents.size() || (studentId < 0)) {
            // if not found, throw StudentNotFoundException
            throw new StudentNotFoundException("Student id not found - " + studentId);
            
        }

        return theStudents.get(studentId);
    }

    // add an exception handler using @ExceptionHandler
    


}







