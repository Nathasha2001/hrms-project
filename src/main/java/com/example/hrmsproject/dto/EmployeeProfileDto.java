package com.example.hrmsproject.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class EmployeeProfileDto {
    private Long id;
    private Long clientId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate joinDate;
    private String status;
    private String address;
    private String nic;
    private LocalDate dob;
    private String role;
    private String employeeTypeName;
    private String departmentName;
    private List<EmergencyContactDto> emergencyContacts;
    private List<LeaveBalanceDto> leaveBalances;
}