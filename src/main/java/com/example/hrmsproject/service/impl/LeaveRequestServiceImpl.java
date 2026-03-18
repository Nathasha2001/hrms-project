package com.example.hrmsproject.service.impl;

import com.example.hrmsproject.entity.LeaveAllocation;
import com.example.hrmsproject.entity.LeaveRequest;
import com.example.hrmsproject.repository.LeaveAllocationRepository;
import com.example.hrmsproject.repository.LeaveRequestRepository;
import com.example.hrmsproject.service.LeaveRequestService;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final LeaveAllocationRepository leaveAllocationRepository;

    public LeaveRequestServiceImpl(LeaveRequestRepository leaveRequestRepository,
                                   LeaveAllocationRepository leaveAllocationRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
        this.leaveAllocationRepository = leaveAllocationRepository;
    }

    @Override
    public LeaveRequest applyLeave(LeaveRequest leaveRequest) {
        LeaveAllocation allocation = leaveAllocationRepository
                .findByEmployeeIdAndLeaveTypeIdAndYear(
                        leaveRequest.getEmployeeId(),
                        leaveRequest.getLeaveTypeId(),
                        Year.now().getValue()
                )
                .orElseThrow(() -> new RuntimeException("Leave allocation not found"));

        int remainingDays = allocation.getTotalDays() - allocation.getUsedDays();

        if (leaveRequest.getTotalDays() > remainingDays) {
            throw new RuntimeException("Not enough leave balance");
        }

        leaveRequest.setStatus("pending");
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
    public Optional<LeaveRequest> getLeaveRequestById(Long id) {
        return leaveRequestRepository.findById(id);
    }

    @Override
    public LeaveRequest approveLeave(Long id) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));

        if (!"pending".equalsIgnoreCase(leaveRequest.getStatus())) {
            throw new RuntimeException("Only pending leave requests can be approved");
        }

        LeaveAllocation allocation = leaveAllocationRepository
                .findByEmployeeIdAndLeaveTypeIdAndYear(
                        leaveRequest.getEmployeeId(),
                        leaveRequest.getLeaveTypeId(),
                        Year.now().getValue()
                )
                .orElseThrow(() -> new RuntimeException("Leave allocation not found"));

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

    @Override
    public LeaveRequest updateLeaveRequest(Long id, LeaveRequest leaveRequest) {
        LeaveRequest existing = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));

        if (!"pending".equalsIgnoreCase(existing.getStatus())) {
            throw new RuntimeException("Only pending leave requests can be edited");
        }

        existing.setStartDate(leaveRequest.getStartDate());
        existing.setEndDate(leaveRequest.getEndDate());
        existing.setTotalDays(leaveRequest.getTotalDays());
        existing.setReason(leaveRequest.getReason());
        existing.setLeaveTypeId(leaveRequest.getLeaveTypeId());

        return leaveRequestRepository.save(existing);
    }
}
