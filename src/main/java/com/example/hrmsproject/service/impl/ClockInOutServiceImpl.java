package com.example.hrmsproject.service.impl;

import com.example.hrmsproject.entity.ClockInOut;
import com.example.hrmsproject.repository.ClockInOutRepository;
import com.example.hrmsproject.service.ClockInOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClockInOutServiceImpl implements ClockInOutService {

    @Autowired
    private ClockInOutRepository clockInOutRepository;

    @Override
    public ClockInOut clockIn(ClockInOut request) {
        ClockInOut existingOpenRecord = clockInOutRepository
                .findTopByEmployeeIdAndClockOutIsNullOrderByClockInDesc(request.getEmployeeId())
                .orElse(null);

        if (existingOpenRecord != null) {
            throw new RuntimeException("Employee already has an active clock-in record");
        }

        request.setClockIn(LocalDateTime.now());
        request.setClockOut(null);
        request.setTotalHours(null);

        return clockInOutRepository.save(request);
    }

    @Override
    public ClockInOut clockOut(Long employeeId) {
        ClockInOut record = clockInOutRepository
                .findTopByEmployeeIdAndClockOutIsNullOrderByClockInDesc(employeeId)
                .orElseThrow(() -> new RuntimeException("No active clock-in record found"));

        LocalDateTime clockOutTime = LocalDateTime.now();
        record.setClockOut(clockOutTime);

        double hours = Duration.between(record.getClockIn(), clockOutTime).toMinutes() / 60.0;

        record.setTotalHours(
                java.math.BigDecimal.valueOf(hours)
                        .setScale(2, java.math.RoundingMode.HALF_UP)
        );

        return clockInOutRepository.save(record);
    }

    @Override
    public List<ClockInOut> getAllRecords() {
        return clockInOutRepository.findAll();
    }

    @Override
    public List<ClockInOut> getByEmployeeId(Long employeeId) {
        return clockInOutRepository.findByEmployeeId(employeeId);
    }
}