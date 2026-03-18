package com.example.hrmsproject.service;

import com.example.hrmsproject.entity.LeaveRequest;

import java.util.List;
import java.util.Optional;

public interface LeaveRequestService {
    LeaveRequest applyLeave(LeaveRequest leaveRequest);
    List<LeaveRequest> getAllLeaveRequests();
    List<LeaveRequest> getLeaveRequestsByEmployeeId(Long employeeId);
    Optional<LeaveRequest> getLeaveRequestById(Long id);
    LeaveRequest approveLeave(Long id);
    LeaveRequest rejectLeave(Long id);
    LeaveRequest updateLeaveRequest(Long id, LeaveRequest leaveRequest);
}