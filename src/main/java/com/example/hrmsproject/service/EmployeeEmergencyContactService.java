package com.example.hrmsproject.service;

import com.example.hrmsproject.entity.EmployeeEmergencyContact;

import java.util.List;

public interface EmployeeEmergencyContactService {
    EmployeeEmergencyContact createContact(EmployeeEmergencyContact contact);
    List<EmployeeEmergencyContact> getContactsByEmployeeId(Long employeeId);
}
