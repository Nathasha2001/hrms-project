package com.example.hrmsproject.service.impl;

import com.example.hrmsproject.entity.LeaveAllocation;
import com.example.hrmsproject.entity.LeaveRequest;
import com.example.hrmsproject.repository.LeaveAllocationRepository;
import com.example.hrmsproject.repository.LeaveRequestRepository;
import com.example.hrmsproject.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private LeaveAllocationRepository leaveAllocationRepository;

    @Override
    public LeaveRequest applyLeave(LeaveRequest leaveRequest) {
        int requestYear = leaveRequest.getStartDate().getYear();

        LeaveAllocation allocation = leaveAllocationRepository
                .findByEmployeeIdAndLeaveTypeIdAndYear(
                        leaveRequest.getEmployeeId(),
                        leaveRequest.getLeaveTypeId(),
                        requestYear
                )
                .orElseThrow(() -> new RuntimeException("No leave allocation found for this employee, leave type, and year"));

        double remainingDays = allocation.getTotalDays() - allocation.getUsedDays();

        if (leaveRequest.getTotalDays() > remainingDays) {
            throw new RuntimeException("Insufficient leave balance");
        }

        leaveRequest.setStatus("pending");
        leaveRequest.setCreatedAt(java.time.LocalDateTime.now());

        return leaveRequestRepository.save(leaveRequest);
    }

    @Override
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepository.findAll();
    }

    @Override
    public List<LeaveRequest> getLeaveRequestsByEmployeeId(Long employeeId) {
        return leaveRequestRepository.findByEmployeeId(employeeId);
    }

    @Override
    public LeaveRequest getLeaveRequestById(Long id) {
        return leaveRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));
    }

    @Override
    public LeaveRequest updateLeaveRequest(Long id, LeaveRequest updatedLeaveRequest) {
        LeaveRequest existing = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));

        if (!"pending".equalsIgnoreCase(existing.getStatus())) {
            throw new RuntimeException("Only pending leave requests can be updated");
        }

        int requestYear = updatedLeaveRequest.getStartDate().getYear();

        LeaveAllocation allocation = leaveAllocationRepository
                .findByEmployeeIdAndLeaveTypeIdAndYear(
                        existing.getEmployeeId(),
                        updatedLeaveRequest.getLeaveTypeId(),
                        requestYear
                )
                .orElseThrow(() -> new RuntimeException("No leave allocation found for updated leave type and year"));

        double remainingDays = allocation.getTotalDays() - allocation.getUsedDays();

        if (updatedLeaveRequest.getTotalDays() > remainingDays) {
            throw new RuntimeException("Insufficient leave balance for updated request");
        }

        existing.setLeaveTypeId(updatedLeaveRequest.getLeaveTypeId());
        existing.setStartDate(updatedLeaveRequest.getStartDate());
        existing.setEndDate(updatedLeaveRequest.getEndDate());
        existing.setTotalDays(updatedLeaveRequest.getTotalDays());
        existing.setReason(updatedLeaveRequest.getReason());

        return leaveRequestRepository.save(existing);
    }

    @Override
    public LeaveRequest approveLeave(Long id) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));

        if (!"pending".equalsIgnoreCase(leaveRequest.getStatus())) {
            throw new RuntimeException("Only pending leave requests can be approved");
        }

        int requestYear = leaveRequest.getStartDate().getYear();

        LeaveAllocation allocation = leaveAllocationRepository
                .findByEmployeeIdAndLeaveTypeIdAndYear(
                        leaveRequest.getEmployeeId(),
                        leaveRequest.getLeaveTypeId(),
                        requestYear
                )
                .orElseThrow(() -> new RuntimeException("No leave allocation found"));

        double remainingDays = allocation.getTotalDays() - allocation.getUsedDays();

        if (leaveRequest.getTotalDays() > remainingDays) {
            throw new RuntimeException("Insufficient leave balance to approve this request");
        }

        allocation.setUsedDays(allocation.getUsedDays() + leaveRequest.getTotalDays());
        leaveAllocationRepository.save(allocation);

        leaveRequest.setStatus("approved");
        return leaveRequestRepository.save(leaveRequest);
    }

    @Override
    public LeaveRequest rejectLeave(Long id) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));

        if (!"pending".equalsIgnoreCase(leaveRequest.getStatus())) {
            throw new RuntimeException("Only pending leave requests can be rejected");
        }

        leaveRequest.setStatus("rejected");
        return leaveRequestRepository.save(leaveRequest);
    }
}