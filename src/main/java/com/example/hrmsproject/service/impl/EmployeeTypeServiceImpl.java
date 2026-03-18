package com.example.hrmsproject.service.impl;

import com.example.hrmsproject.entity.EmployeeType;
import com.example.hrmsproject.repository.EmployeeTypeRepository;
import com.example.hrmsproject.service.EmployeeTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeTypeServiceImpl implements EmployeeTypeService {

    private final EmployeeTypeRepository employeeTypeRepository;

    public EmployeeTypeServiceImpl(EmployeeTypeRepository employeeTypeRepository) {
        this.employeeTypeRepository = employeeTypeRepository;
    }

    @Override
    public EmployeeType saveEmployeeType(EmployeeType employeeType) {
        return employeeTypeRepository.save(employeeType);
    }

    @Override
    public List<EmployeeType> getAllEmployeeTypes() {
        return employeeTypeRepository.findAll();
    }

    @Override
    public List<EmployeeType> getEmployeeTypesByClientId(Long clientId) {
        return employeeTypeRepository.findByClientId(clientId);
    }

    @Override
    public Optional<EmployeeType> getEmployeeTypeById(Long id) {
        return employeeTypeRepository.findById(id);
    }
}
