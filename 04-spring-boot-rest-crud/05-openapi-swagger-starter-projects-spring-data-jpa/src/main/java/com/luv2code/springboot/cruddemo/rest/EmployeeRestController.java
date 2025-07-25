package com.luv2code.springboot.cruddemo.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    private ObjectMapper objectMapper;

    // quick and dirity : inject emloyeeDAO
    @Autowired
public EmployeeRestController(EmployeeService theEmployeeService , ObjectMapper thObjectMapper) {
        employeeService = theEmployeeService;
        objectMapper = thObjectMapper;
    }

    // expose "/employees" and return list of employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    // add mapping for GET /employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee theEmployee = employeeService.findById(employeeId);
        if(theEmployee == null){
            throw new RuntimeException("Employee id not found - " + employeeId );
        }
        return theEmployee;
    }


    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee)  {
        theEmployee.setId(0);
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;

}


    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {
       Employee dbEmployee = employeeService.save(theEmployee);
       return dbEmployee;
    }

    @PatchMapping("/employees/{employeeId}")
    public Employee patchEmployee(@PathVariable int employeeId, @RequestBody Map <String, Object> patchPayload){
        Employee tempEmployee = employeeService.findById(employeeId);

        // throw exception if null
        if (tempEmployee == null){
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        // throw exception if request body contains "id" key
        if (patchPayload.containsKey("id")){
            throw new RuntimeException("employee id not allowed in request body - " + employeeId);
        }
        Employee patchedEmployee = apply(patchPayload , tempEmployee);
        Employee dbEmployee = employeeService.save(patchedEmployee);
         return dbEmployee;
    }

    private Employee apply(Map<String, Object> patchPayload, Employee tempEmployee) {

        // Convert employee object to a JSON object node
        ObjectNode employeeNode = objectMapper.convertValue(tempEmployee, ObjectNode.class);

        // convert the patchPayload map to aJSON object nade
        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);

        //Merg the patch updates into the employee node
        employeeNode.setAll(patchNode);
        return objectMapper.convertValue(employeeNode, Employee.class);

    }

    // add mapping for DELETE /employees/{employeeId}
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee tempEmployee = employeeService.findById(employeeId);
        if(tempEmployee == null){
            throw new RuntimeException("Employee id not found - " + employeeId );
        }
        employeeService.deleteById(employeeId);
        return "Deleted employee id - " + employeeId;
    }

}




