package com.example.hrmsproject.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_emergency_contacts")
public class EmployeeEmergencyContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    public EmployeeEmergencyContact() {
    }

    public EmployeeEmergencyContact(Long employeeId, String name, String phone) {
        this.employeeId = employeeId;
        this.name = name;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}