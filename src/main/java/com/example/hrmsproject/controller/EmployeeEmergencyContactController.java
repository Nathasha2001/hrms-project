package com.example.hrmsproject.controller;

import com.example.hrmsproject.entity.EmployeeEmergencyContact;
import com.example.hrmsproject.service.EmployeeEmergencyContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee-emergency-contacts")
@CrossOrigin(origins = "*")
public class EmployeeEmergencyContactController {

    @Autowired
    private EmployeeEmergencyContactService service;

    @PostMapping
    public EmployeeEmergencyContact saveContact(@RequestBody EmployeeEmergencyContact contact) {
        return service.saveContact(contact);
    }

    @GetMapping("/employee/{employeeId}")
    public List<EmployeeEmergencyContact> getContactsByEmployeeId(@PathVariable Long employeeId) {
        return service.getContactsByEmployeeId(employeeId);
    }

    @PutMapping("/{id}")
    public EmployeeEmergencyContact updateContact(@PathVariable Long id, @RequestBody EmployeeEmergencyContact contact) {
        return service.updateContact(id, contact);
    }

    @DeleteMapping("/{id}")
    public String deleteContact(@PathVariable Long id) {
        service.deleteContact(id);
        return "Emergency contact deleted successfully";
    }
}