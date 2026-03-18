package com.example.hrmsproject.controller;

import com.example.hrmsproject.entity.LeaveRequest;
import com.example.hrmsproject.service.LeaveRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave-requests")
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }

    @PostMapping
    public ResponseEntity<LeaveRequest> applyLeave(@RequestBody LeaveRequest leaveRequest) {
        return ResponseEntity.ok(leaveRequestService.applyLeave(leaveRequest));
    }

    @GetMapping
    public ResponseEntity<List<LeaveRequest>> getAll() {
        return ResponseEntity.ok(leaveRequestService.getAllLeaveRequests());
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<LeaveRequest>> getByEmployeeId(@PathVariable Long employeeId) {
        return ResponseEntity.ok(leaveRequestService.getLeaveRequestsByEmployeeId(employeeId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeaveRequest> updateLeave(@PathVariable Long id, @RequestBody LeaveRequest leaveRequest) {
        return ResponseEntity.ok(leaveRequestService.updateLeaveRequest(id, leaveRequest));
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<LeaveRequest> approveLeave(@PathVariable Long id) {
        return ResponseEntity.ok(leaveRequestService.approveLeave(id));
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<LeaveRequest> rejectLeave(@PathVariable Long id) {
        return ResponseEntity.ok(leaveRequestService.rejectLeave(id));
    }
}
