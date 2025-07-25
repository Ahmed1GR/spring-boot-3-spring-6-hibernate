package com.luv2code.cruddemo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.cruddemo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class StudentDAOImpl implements StudentDAO {

    // define field for entity manager
    private EntityManager entityManager;    

    // inject entity manager using constructor injection

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }    
    // implement save method
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);       
}
    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
}
    @Override
    public List<Student> findAll() {
        // create a query
        TypedQuery<Student> theQuery = 
                entityManager.createQuery("FROM Student", Student.class);
        // return the theQuery results
        return theQuery.getResultList();

    }
    @Override
    public List<Student> findByLastName(String lastName) {
        // create a query
        TypedQuery<Student> theQuery = 
                entityManager.createQuery("FROM Student WHERE lastName=:theData", Student.class);


        // set query parameters
        theQuery.setParameter("theData", lastName);


        // return query results
        return theQuery.getResultList();



    }
    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }
    @Override
    @Transactional
    public void delete(Integer id) {

        // retrieve student based on the id: primary key
        Student theStudent = entityManager.find(Student.class, id);

        // delete the student
        entityManager.remove(theStudent);
    }
    @Override
    @Transactional
    public int deleteAll() {

        int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return numRowsDeleted;
    }
}
