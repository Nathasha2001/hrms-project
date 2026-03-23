package com.example.hrmsproject.service.impl;

import com.example.hrmsproject.entity.EmployeeEmergencyContact;
import com.example.hrmsproject.repository.EmployeeEmergencyContactRepository;
import com.example.hrmsproject.service.EmployeeEmergencyContactService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeEmergencyContactServiceImpl implements EmployeeEmergencyContactService {

    private final EmployeeEmergencyContactRepository repository;

    public EmployeeEmergencyContactServiceImpl(EmployeeEmergencyContactRepository repository) {
        this.repository = repository;
    }

    @Override
    public EmployeeEmergencyContact createContact(EmployeeEmergencyContact contact) {
        return repository.save(contact);
    }

    @Override
    public List<EmployeeEmergencyContact> getContactsByEmployeeId(Long employeeId) {
        return repository.findByEmployeeId(employeeId);
    }
}