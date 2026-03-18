package com.example.hrmsproject.service;

import com.example.hrmsproject.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    List<Employee> getEmployeesByClientId(Long clientId);
    Optional<Employee>getEmployeeById(Long id);
    Employee updateEmployee (Long id, Employee employee);
    void deleteEmployee(Long id);


}
