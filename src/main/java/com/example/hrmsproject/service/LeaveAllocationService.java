package com.example.hrmsproject.service;

import com.example.hrmsproject.entity.LeaveAllocation;

import java.util.List;

public interface LeaveAllocationService {
    List<LeaveAllocation> getAllLeaveAllocations();
    List<LeaveAllocation>getByEmployeeId(Long employeeId);

}
