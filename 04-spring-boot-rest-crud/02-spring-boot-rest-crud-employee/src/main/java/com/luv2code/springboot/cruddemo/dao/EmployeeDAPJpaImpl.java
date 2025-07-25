package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EmployeeDAPJpaImpl implements EmployeeDAO{

    // define field for entity manager
    private EntityManager entityManager;


    // set up constructor injection
    @Autowired

    public EmployeeDAPJpaImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }
    @Override
    public List<Employee> findAll() {

        // create a query
        TypedQuery<Employee> theQuery = 
                        entityManager.createQuery("from Employee", Employee.class);
        
        
        // execute query and get result list
        List<Employee> employees = theQuery.getResultList();

        
        // return results
        return employees;

    }
    @Override
    public Employee findById(int theId) {

    // find the employee
    Employee theEmployee = entityManager.find(Employee.class, theId);

    // return the employee
    return theEmployee;
    
    }
    @Override
    public Employee save(Employee theEmployee) {

    // save or update the employee
    Employee dbEmployee = entityManager.merge(theEmployee);

    // return the dbEmlpoyee
        return dbEmployee;
    }
    @Override
    public void deleteById(int theId) {
        Employee theEmployee = entityManager.find(Employee.class , theId);

        // remove employee
        entityManager.remove(theEmployee);
 }


}
