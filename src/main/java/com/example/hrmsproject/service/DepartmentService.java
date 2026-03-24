package com.example.hrmsproject.service;

import com.example.hrmsproject.entity.Department;

import java.util.List;

public interface DepartmentService {
    Department saveDepartment(Department department);
    List<Department> getDepartmentsByClientId(Long clientId);
    Department getDepartmentById(Long id);
    Department updateDepartment(Long id, Department department);
    void deleteDepartment(Long id);
}