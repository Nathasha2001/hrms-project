package com.example.hrmsproject.controller;

import com.example.hrmsproject.entity.LeaveType;
import com.example.hrmsproject.service.LeaveTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave-types")
public class LeaveTypeController {

    private final LeaveTypeService leaveTypeService;

    public LeaveTypeController(LeaveTypeService leaveTypeService) {
        this.leaveTypeService = leaveTypeService;
    }

    @PostMapping
    public ResponseEntity<LeaveType> createLeaveType(@RequestBody LeaveType leaveType) {
        return ResponseEntity.ok(leaveTypeService.saveLeaveType(leaveType));
    }

    @GetMapping
    public ResponseEntity<List<LeaveType>> getAllLeaveTypes() {
        return ResponseEntity.ok(leaveTypeService.getAllLeaveTypes());
    }


}
