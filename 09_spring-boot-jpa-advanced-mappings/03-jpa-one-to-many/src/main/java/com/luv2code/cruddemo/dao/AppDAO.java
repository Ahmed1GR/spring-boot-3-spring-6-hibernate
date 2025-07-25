package com.luv2code.cruddemo.dao;

import java.util.List;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;

public interface AppDAO {

    void save(Instructor tempInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);

    List<Course> findCoursesByInstructorId(int theId);

    void update(Instructor tempInstructor);

    Instructor findInstructorByIdJoinFetch(int theId);

    void update(Course tempCourse);

    Course findCourseById(int theId);

    void deleteCourseById(int theId);

}
