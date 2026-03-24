package com.example.hrmsproject.controller;

import com.example.hrmsproject.entity.ClockInOut;
import com.example.hrmsproject.service.ClockInOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clock-in-out")
@CrossOrigin(origins = "*")
public class ClockInOutController {

    @Autowired
    private ClockInOutService clockInOutService;

    @PostMapping("/clock-in")
    public ClockInOut clockIn(@RequestBody ClockInOut request) {
        return clockInOutService.clockIn(request);
    }

    @PutMapping("/clock-out/{employeeId}")
    public ClockInOut clockOut(@PathVariable Long employeeId) {
        return clockInOutService.clockOut(employeeId);
    }

    @GetMapping
    public List<ClockInOut> getAllRecords() {
        return clockInOutService.getAllRecords();
    }

    @GetMapping("/employee/{employeeId}")
    public List<ClockInOut> getByEmployeeId(@PathVariable Long employeeId) {
        return clockInOutService.getByEmployeeId(employeeId);
    }
}