package com.luv2code.springboot.cruddemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO thEmployeeDAO){
        employeeDAO = thEmployeeDAO;
    }
    @Override
    public List<Employee> findAll() {
    return employeeDAO.findAll();
        }
    @Override
    
    public Employee findById(int theId) {
        return employeeDAO.findById(theId);
    }
    @Transactional
    @Override
    public Employee save(Employee theEmployee) {
        return employeeDAO.save(theEmployee);
        }
    @Transactional
    @Override
    public void deleteById(int theId) {
        employeeDAO.deleteById(theId);

    }




}
