package com.example.hrmsproject.service.impl;

import com.example.hrmsproject.entity.Department;
import com.example.hrmsproject.repository.DepartmentRepository;
import com.example.hrmsproject.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getDepartmentsByClientId(Long clientId) {
        return departmentRepository.findByClientId(clientId);
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    @Override
    public Department updateDepartment(Long id, Department department) {
        Department existing = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        existing.setName(department.getName());
        existing.setClientId(department.getClientId());
        return departmentRepository.save(existing);
    }

    @Override
    public void deleteDepartment(Long id) {
        Department existing = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        departmentRepository.delete(existing);
    }
}