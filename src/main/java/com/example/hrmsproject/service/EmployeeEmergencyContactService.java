package com.example.hrmsproject.service;

import com.example.hrmsproject.entity.EmployeeEmergencyContact;

import java.util.List;

public interface EmployeeEmergencyContactService {
    EmployeeEmergencyContact saveContact(EmployeeEmergencyContact contact);
    List<EmployeeEmergencyContact> getContactsByEmployeeId(Long employeeId);
    EmployeeEmergencyContact updateContact(Long id, EmployeeEmergencyContact contact);
    void deleteContact(Long id);
}