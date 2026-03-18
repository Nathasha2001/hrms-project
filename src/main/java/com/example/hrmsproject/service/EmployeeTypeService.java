package com.example.hrmsproject.service;

import com.example.hrmsproject.entity.EmployeeType;

import java.util.List;
import java.util.Optional;

public interface EmployeeTypeService {
    EmployeeType saveEmployeeType(EmployeeType employeeType);
    List<EmployeeType> getAllEmployeeTypes();
    List<EmployeeType> getEmployeeTypesByClientId(Long clientId);
    Optional<EmployeeType> getEmployeeTypeById (Long id);
}
