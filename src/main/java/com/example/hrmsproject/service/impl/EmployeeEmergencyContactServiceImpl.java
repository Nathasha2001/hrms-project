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
    public EmployeeEmergencyContact saveContact(EmployeeEmergencyContact contact) {
        if (contact.getEmployeeId() == null) {
            throw new RuntimeException("employeeId is required");
        }

        if (contact.getName() == null || contact.getName().trim().isEmpty()) {
            throw new RuntimeException("name is required");
        }

        if (contact.getPhone() == null || contact.getPhone().trim().isEmpty()) {
            throw new RuntimeException("phone is required");
        }

        return repository.save(contact);
    }

    @Override
    public List<EmployeeEmergencyContact> getContactsByEmployeeId(Long employeeId) {
        return repository.findByEmployeeId(employeeId);
    }

    @Override
    public EmployeeEmergencyContact updateContact(Long id, EmployeeEmergencyContact contact) {
        EmployeeEmergencyContact existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Emergency contact not found"));

        if (contact.getEmployeeId() == null) {
            throw new RuntimeException("employeeId is required");
        }

        if (contact.getName() == null || contact.getName().trim().isEmpty()) {
            throw new RuntimeException("name is required");
        }

        if (contact.getPhone() == null || contact.getPhone().trim().isEmpty()) {
            throw new RuntimeException("phone is required");
        }

        existing.setEmployeeId(contact.getEmployeeId());
        existing.setName(contact.getName());
        existing.setPhone(contact.getPhone());

        return repository.save(existing);
    }

    @Override
    public void deleteContact(Long id) {
        EmployeeEmergencyContact existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Emergency contact not found"));

        repository.delete(existing);
    }
}