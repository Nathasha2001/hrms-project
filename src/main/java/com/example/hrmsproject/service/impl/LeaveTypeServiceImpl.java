package com.example.hrmsproject.service.impl;

import com.example.hrmsproject.entity.LeaveType;
import com.example.hrmsproject.repository.LeaveTypeRepository;
import com.example.hrmsproject.service.LeaveTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {

    private final LeaveTypeRepository leaveTypeRepository;

    public LeaveTypeServiceImpl(LeaveTypeRepository leaveTypeRepository) {
        this.leaveTypeRepository = leaveTypeRepository;
    }

    @Override
    public LeaveType saveLeaveType(LeaveType leaveType) {
        return leaveTypeRepository.save(leaveType);
    }

    @Override
    public List<LeaveType> getAllLeaveTypes() {
        return leaveTypeRepository.findAll();
    }

    @Override
    public Optional<LeaveType> getLeaveTypeById(Long id) {
        return leaveTypeRepository.findById(id);
    }
}