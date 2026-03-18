package com.example.hrmsproject.repository;

import com.example.hrmsproject.entity.EmployeeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeTypeRepository extends JpaRepository<EmployeeType, Long> {
    List<EmployeeType> findByClientId(Long ClientId);
}
