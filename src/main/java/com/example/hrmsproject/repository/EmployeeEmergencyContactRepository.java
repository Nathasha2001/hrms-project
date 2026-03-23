package com.example.hrmsproject.repository;

import com.example.hrmsproject.entity.EmployeeEmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeEmergencyContactRepository extends JpaRepository<EmployeeEmergencyContact, Long> {
    List<EmployeeEmergencyContact> findByEmployeeId(Long employeeId);
}