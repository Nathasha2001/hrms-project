package com.example.hrmsproject.service.impl;

import com.example.hrmsproject.entity.LeaveAllocation;
import com.example.hrmsproject.repository.LeaveAllocationRepository;
import com.example.hrmsproject.service.LeaveAllocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveAllocationServiceImpl implements LeaveAllocationService {

    private final LeaveAllocationRepository leaveAllocationRepository;

    public LeaveAllocationServiceImpl(LeaveAllocationRepository leaveAllocationRepository) {
        this.leaveAllocationRepository = leaveAllocationRepository;
    }

    @Override
    public List<LeaveAllocation> getAllLeaveAllocations() {
        return leaveAllocationRepository.findAll();
    }

    @Override
    public List<LeaveAllocation> getByEmployeeId(Long employeeId) {
        return leaveAllocationRepository.findByEmployeeId(employeeId);
    }
}