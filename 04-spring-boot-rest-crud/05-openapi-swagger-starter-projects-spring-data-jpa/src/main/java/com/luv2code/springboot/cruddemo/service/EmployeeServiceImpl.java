package com.luv2code.springboot.cruddemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.springboot.cruddemo.dao.EmployeeRepository;
import com.luv2code.springboot.cruddemo.entity.Employee;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository thEmployeeRepository){
        employeeRepository = thEmployeeRepository;
    }
    @Override
    public List<Employee> findAll() {
    return employeeRepository.findAll();
        }
    @Override
    
    public Employee findById(int theId) {
        Optional<Employee> result = employeeRepository.findById(theId);
        Employee thEmployee = null;
        if(result.isPresent()){
            thEmployee = result.get();
        }
        else{
            throw new RuntimeException("Did nnot find employee id - " + theId);
        }
        return thEmployee;
    }
    @Override
    public Employee save(Employee theEmployee) {
        return employeeRepository.save(theEmployee);
        }
    
    @Override
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);

    }
}
