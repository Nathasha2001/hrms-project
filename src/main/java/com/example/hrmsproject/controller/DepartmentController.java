package com.example.hrmsproject.controller;

import com.example.hrmsproject.entity.Department;
import com.example.hrmsproject.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        return ResponseEntity.ok(departmentService.createDepartment(department));
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Department>> getByClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(departmentService.getDepartmentsByClientId(clientId));
    }
}