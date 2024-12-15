package com.spring.EMS.service;

import com.spring.EMS.Exception.ResourceNotFoundException;
import com.spring.EMS.dto.EmployeeDto;
import com.spring.EMS.entity.Employee;
import com.spring.EMS.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl {

    @Autowired
    EmployeeRepo er;

    public ResponseEntity<List<Employee>> getEmployees() {
        return new ResponseEntity<>(er.findAll(), HttpStatus.OK);
    }
    public Employee getEmployee(Long id) {
        return er.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee not found for given Id: " + id));
    }

    public ResponseEntity<Employee> addEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setEmail(employeeDto.getEmail());
        employee.setPhone(employeeDto.getPhone());
        return new ResponseEntity<>(er.save(employee), HttpStatus.CREATED);
    }

    public ResponseEntity<Employee> updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = er.findById(id)
                        .orElseThrow(()-> new ResourceNotFoundException("Employee not found for given Id: " + id));

        employee.setName(employeeDto.getName());
        employee.setEmail(employeeDto.getEmail());
        employee.setPhone(employeeDto.getPhone());

        return new ResponseEntity<>(er.save(employee), HttpStatus.OK);
    }

    public ResponseEntity<String> deleteEmployee(Long id) {

        er.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not found for given Id: " + id));

        er.deleteById(id);
        return ResponseEntity.ok("Employee with id: " + id + " is Deleted Successfully");

    }
}
