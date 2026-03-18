package com.example.hrmsproject.controller;

import com.example.hrmsproject.entity.LeaveAllocation;
import com.example.hrmsproject.service.LeaveAllocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave-allocations")
public class LeaveAllocationController {

    private final LeaveAllocationService leaveAllocationService;

    public LeaveAllocationController(LeaveAllocationService leaveAllocationService) {
        this.leaveAllocationService = leaveAllocationService;
    }

    @GetMapping
    public ResponseEntity<List<LeaveAllocation>> getAll() {
        return ResponseEntity.ok(leaveAllocationService.getAllLeaveAllocations());
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<LeaveAllocation>> getByEmployeeId(@PathVariable Long employeeId) {
        return ResponseEntity.ok(leaveAllocationService.getByEmployeeId(employeeId));
    }
}