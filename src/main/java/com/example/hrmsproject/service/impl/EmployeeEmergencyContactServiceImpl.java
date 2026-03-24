package com.example.hrmsproject.service.impl;

import com.example.hrmsproject.entity.EmployeeEmergencyContact;
import com.example.hrmsproject.repository.EmployeeEmergencyContactRepository;
import com.example.hrmsproject.service.EmployeeEmergencyContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeEmergencyContactServiceImpl implements EmployeeEmergencyContactService {

    @Autowired
    private EmployeeEmergencyContactRepository repository;

    @Override
    public EmployeeEmergencyContact saveContact(EmployeeEmergencyContact contact) {
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

        existing.setEmployeeId(contact.getEmployeeId());
        existing.setName(contact.getName());
        existing.setRelationship(contact.getRelationship());
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