package com.spring.EMS.controller;

import com.spring.EMS.dto.EmployeeDto;
import com.spring.EMS.entity.Employee;
import com.spring.EMS.service.EmployeeServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees() {
        return employeeServiceImpl.getEmployees();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employeeServiceImpl.getEmployee(id);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> addEmployee (@RequestBody EmployeeDto employeeDto) {
        return employeeServiceImpl.addEmployee(employeeDto);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        return employeeServiceImpl.updateEmployee(id, employeeDto);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        return employeeServiceImpl.deleteEmployee(id);
    }

}
