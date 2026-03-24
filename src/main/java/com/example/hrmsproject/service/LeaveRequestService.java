package com.example.hrmsproject.service;

import com.example.hrmsproject.entity.LeaveRequest;

import java.util.List;

public interface LeaveRequestService {
    LeaveRequest applyLeave(LeaveRequest leaveRequest);
    List<LeaveRequest> getAllLeaveRequests();
    List<LeaveRequest> getLeaveRequestsByEmployeeId(Long employeeId);
    LeaveRequest approveLeave(Long id);
    LeaveRequest rejectLeave(Long id);
    LeaveRequest updateLeaveRequest(Long id, LeaveRequest leaveRequest);
    LeaveRequest getLeaveRequestById(Long id);
}