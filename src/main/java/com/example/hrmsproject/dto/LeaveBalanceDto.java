package com.example.hrmsproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LeaveBalanceDto {
    private Long leaveTypeId;
    private String leaveTypeName;
    private Integer totalDays;
    private Integer usedDays;
    private Integer remainingDays;
}