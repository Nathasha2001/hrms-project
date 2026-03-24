package com.example.hrmsproject.service;

import com.example.hrmsproject.entity.ClockInOut;

import java.util.List;

public interface ClockInOutService {
    ClockInOut clockIn(ClockInOut request);
    ClockInOut clockOut(Long employeeId);
    List<ClockInOut> getAllRecords();
    List<ClockInOut> getByEmployeeId(Long employeeId);
}