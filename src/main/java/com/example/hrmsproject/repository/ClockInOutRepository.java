package com.example.hrmsproject.repository;

import com.example.hrmsproject.entity.ClockInOut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClockInOutRepository extends JpaRepository<ClockInOut, Long> {
    Optional<ClockInOut> findTopByEmployeeIdAndClockOutIsNullOrderByClockInDesc(Long employeeId);
    List<ClockInOut> findByEmployeeId(Long employeeId);
}