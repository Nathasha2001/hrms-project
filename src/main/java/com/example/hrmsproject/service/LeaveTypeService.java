package com.example.hrmsproject.service;

import com.example.hrmsproject.entity.LeaveType;

import java.util.List;
import java.util.Optional;

public interface LeaveTypeService {
    LeaveType saveLeaveType(LeaveType leaveType);
    List<LeaveType> getAllLeaveTypes();
    Optional<LeaveType> getLeaveTypeById(Long id);
}