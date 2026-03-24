package com.example.hrmsproject.controller;

import com.example.hrmsproject.dto.EmergencyContactRequestDto;
import com.example.hrmsproject.entity.EmployeeEmergencyContact;
import com.example.hrmsproject.service.EmployeeEmergencyContactService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee-emergency-contacts")
@CrossOrigin(origins = "*")
public class EmployeeEmergencyContactController {

    private final EmployeeEmergencyContactService service;

    public EmployeeEmergencyContactController(EmployeeEmergencyContactService service) {
        this.service = service;
    }

    @PostMapping
    public EmployeeEmergencyContact saveContact(@RequestBody EmergencyContactRequestDto request) {

        if (request.getEmployeeId() == null) {
            throw new RuntimeException("employeeId is required");
        }

        EmployeeEmergencyContact contact = new EmployeeEmergencyContact();
        contact.setEmployeeId(request.getEmployeeId());
        contact.setName(request.getName());
        contact.setPhone(request.getPhone());

        return service.saveContact(contact);
    }

    @GetMapping("/employee/{employeeId}")
    public List<EmployeeEmergencyContact> getContactsByEmployeeId(@PathVariable Long employeeId) {
        return service.getContactsByEmployeeId(employeeId);
    }

    @PutMapping("/{id}")
    public EmployeeEmergencyContact updateContact(@PathVariable Long id,
                                                  @RequestBody EmergencyContactRequestDto request) {

        if (request.getEmployeeId() == null) {
            throw new RuntimeException("employeeId is required");
        }

        EmployeeEmergencyContact contact = new EmployeeEmergencyContact();
        contact.setEmployeeId(request.getEmployeeId());
        contact.setName(request.getName());
        contact.setPhone(request.getPhone());

        return service.updateContact(id, contact);
    }

    @DeleteMapping("/{id}")
    public String deleteContact(@PathVariable Long id) {
        service.deleteContact(id);
        return "Emergency contact deleted successfully";
    }
}