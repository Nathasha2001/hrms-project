package com.example.hrmsproject.controller;


import com.example.hrmsproject.entity.EmployeeType;
import com.example.hrmsproject.service.EmployeeTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee-types")
public class EmployeeTypeController {

    private final EmployeeTypeService employeeTypeService;

    public EmployeeTypeController(EmployeeTypeService employeeTypeService) {
        this.employeeTypeService = employeeTypeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeType>createEmployeeType(@RequestBody EmployeeType employeeType){
        return ResponseEntity.ok(employeeTypeService.saveEmployeeType(employeeType));

    }

    @GetMapping
    public ResponseEntity<List<EmployeeType>> getAllEmployeeTypes() {
        return ResponseEntity.ok(employeeTypeService.getAllEmployeeTypes());
    }



}
