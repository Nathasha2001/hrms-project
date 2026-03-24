package com.example.hrmsproject.dto;

public class EmergencyContactRequestDto {

    private Long employeeId;
    private String name;
    private String phone;

    public EmergencyContactRequestDto() {
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
