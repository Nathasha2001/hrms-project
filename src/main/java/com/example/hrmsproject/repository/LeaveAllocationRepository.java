package com.example.hrmsproject.repository;

import com.example.hrmsproject.entity.LeaveAllocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LeaveAllocationRepository extends JpaRepository<LeaveAllocation, Long> {
    List<LeaveAllocation> findByEmployeeId(Long employeeId);


    Optional<LeaveAllocation> findByEmployeeIdAndLeaveTypeIdAndYear(
            Long employeeId,
            Long leaveTypeId,
            Integer year);
}
