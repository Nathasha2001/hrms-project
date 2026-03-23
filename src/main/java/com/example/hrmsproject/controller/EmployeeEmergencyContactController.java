package com.example.hrmsproject.controller;

import com.example.hrmsproject.entity.EmployeeEmergencyContact;
import com.example.hrmsproject.service.EmployeeEmergencyContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emergency-contacts")
public class EmployeeEmergencyContactController {

    private final EmployeeEmergencyContactService service;

    public EmployeeEmergencyContactController(EmployeeEmergencyContactService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EmployeeEmergencyContact> create(@RequestBody EmployeeEmergencyContact contact) {
        return ResponseEntity.ok(service.createContact(contact));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<EmployeeEmergencyContact>> getByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(service.getContactsByEmployeeId(employeeId));
    }
}