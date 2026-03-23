package com.example.hrmsproject.service;

import com.example.hrmsproject.entity.Department;

import java.util.List;

public interface DepartmentService {
    Department createDepartment(Department department);
    List<Department> getDepartmentsByClientId(Long clientId);
}