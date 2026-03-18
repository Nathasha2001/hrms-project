package com.example.hrmsproject.repository;

import com.example.hrmsproject.entity.LeaveAllocation;
import com.example.hrmsproject.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByEmployeeId(Long employeeId);
}
