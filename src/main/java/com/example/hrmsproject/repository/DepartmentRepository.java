package com.example.hrmsproject.repository;

import com.example.hrmsproject.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByClientId(Long clientId);
}