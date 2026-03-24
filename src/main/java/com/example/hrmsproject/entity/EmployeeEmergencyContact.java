package com.example.hrmsproject.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "employee_emergency_contacts")
@Data
public class EmployeeEmergencyContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long hetEmployeeId(){
        return employeeId;
    }
    public void setEmployeeId(Long employeeId){
        this.employeeId = employeeId;
    }

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    private String name;
    private String phone;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;


}