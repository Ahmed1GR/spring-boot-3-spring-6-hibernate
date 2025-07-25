package com.luv2code.cruddemo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class AppDAOImpl implements AppDAO {

    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    public AppDAOImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public void save(Instructor tempInstructor) {
        // Implementation for saving the instructor
        // This could involve using an EntityManager or a Session to persist the entity
        entityManager.persist(tempInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {

        return entityManager.find(Instructor.class, theId);

    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        
        // retrieve the instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);

        // remove the associated object reference
        // break bi-directional link

        // get courses for instructor
        List<Course> courses = tempInstructor.getCourses();

       for (Course tempCourse : courses){
        tempCourse.setInstructor(null);
       }

        // delete the instructor
        entityManager.remove(tempInstructor);        
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        
        // retrieve the instructor detail
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);

        // remove the associated object reference
        // break bi-directional link

        if (tempInstructorDetail != null) {
            tempInstructorDetail.getInstructor().setInstructorDetail(null);
        }

        // delete the instructor detail
        entityManager.remove(tempInstructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {

        // creat query
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data", Course.class);

        // set query parameter
        query.setParameter("data", theId);

        // execute query and get result list
        List<Course> courses = query.getResultList();
        
        return courses;
         
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {

        // get the instructor
        TypedQuery<Instructor> query = entityManager.createQuery("select i from Instructor i JOIN FETCH i.courses JOIN FETCH i.instructorDetail where i.id = :data", Instructor.class);

        // set query parameter
        query.setParameter("data", theId);

        // execute query and get single result
        Instructor theInstructor = query.getSingleResult();
        
        return theInstructor;
    }

    @Override
    @Transactional
    public void update(Instructor tempInstructor) {

        entityManager.merge(tempInstructor);
    }

    @Override
    @Transactional
    public void update(Course tempCourse) {

        entityManager.merge(tempCourse);
    }

    @Override
    public Course findCourseById(int theId) {

        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {

        // retrieve the course
        Course tempCourse = entityManager.find(Course.class, theId);

        // remove the associated object reference
        entityManager.remove(tempCourse);
    }

    @Override
    @Transactional
    public void save(Course tempCourse) {

        entityManager.persist(tempCourse);
    }

    @Override
    public Course findCourseAndReviewByCourseId(int theId) {

        // create query
        TypedQuery<Course> query = entityManager.createQuery(
            "select c from Course c JOIN FETCH c.reviews where c.id = :data"
            , Course.class);

        // set query parameter
        query.setParameter("data", theId);

        // execute query and get single result
        Course theCourse = query.getSingleResult();
        
        return theCourse;
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int theId) {

        // create query
        TypedQuery<Course> query = entityManager.createQuery(
            "select c from Course c JOIN FETCH c.students where c.id = :data"
            , Course.class);

        // set query parameter
        query.setParameter("data", theId);

        // execute query and get single result
        Course theCourse = query.getSingleResult();
        
        return theCourse;
    }

    @Override
    public Student findStudensAndCoursesByStudentId(int theId) {

                // create query
        TypedQuery<Student> query = entityManager.createQuery(
            "select s from Student s JOIN FETCH s.courses where s.id = :data"
            , Student.class);

        // set query parameter
        query.setParameter("data", theId);

        // execute query and get single result
        Student student = query.getSingleResult();
        
        return student;
    }

    @Override
    @Transactional
    public void update(Student tempStudent) {

        entityManager.merge(tempStudent);
    }

    @Override
    @Transactional
    public void deleteStudentById(int theId) {

        // delete the student
        Student tempStudent = entityManager.find(Student.class, theId);

        if(tempStudent !=null){
            List<Course> courses = tempStudent.getCourses();

            for (Course course : courses) {
                 course.getStudents().remove(tempStudent);
                }
        }

        entityManager.remove(tempStudent);
    }

}
