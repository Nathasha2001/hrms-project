package com.example.hrmsproject.service.impl;

import com.example.hrmsproject.entity.Department;
import com.example.hrmsproject.repository.DepartmentRepository;
import com.example.hrmsproject.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getDepartmentsByClientId(Long clientId) {
        return departmentRepository.findByClientId(clientId);
    }
}