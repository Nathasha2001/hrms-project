package com.example.hrmsproject.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table (name = "leave_allocations")
@Data
public class LeaveAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "leave_type_id")
    private Long leaveTypeId;

    @Column(name = "total_days")
    private Integer totalDays;

    @Column(name= "used_days")
    private Integer usedDays;

    private Integer year;

}
