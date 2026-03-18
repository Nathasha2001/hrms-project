package com.example.hrmsproject.service.impl;

import com.example.hrmsproject.entity.Employee;
import com.example.hrmsproject.entity.EmployeeType;
import com.example.hrmsproject.entity.LeaveAllocation;
import com.example.hrmsproject.entity.LeaveType;
import com.example.hrmsproject.repository.EmployeeRepository;
import com.example.hrmsproject.repository.EmployeeTypeRepository;
import com.example.hrmsproject.repository.LeaveAllocationRepository;
import com.example.hrmsproject.repository.LeaveTypeRepository;
import com.example.hrmsproject.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeTypeRepository employeeTypeRepository;
    private final LeaveTypeRepository leaveTypeRepository;
    private final LeaveAllocationRepository leaveAllocationRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               EmployeeTypeRepository employeeTypeRepository,
                               LeaveTypeRepository leaveTypeRepository,
                               LeaveAllocationRepository leaveAllocationRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeTypeRepository = employeeTypeRepository;
        this.leaveTypeRepository = leaveTypeRepository;
        this.leaveAllocationRepository = leaveAllocationRepository;
    }

    @Transactional //without this, partial data gets saved
    @Override
    public Employee saveEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeType employeeType = employeeTypeRepository.findById(savedEmployee.getEmployeeTypeId())
                .orElseThrow(() -> new RuntimeException("Employee type not found"));

        LeaveType annualLeaveType = leaveTypeRepository
                .findByClientIdAndNameIgnoreCase(savedEmployee.getClientId(), "Annual")
                .orElseThrow(() -> new RuntimeException("Annual leave type not found"));

        LeaveType casualLeaveType = leaveTypeRepository
                .findByClientIdAndNameIgnoreCase(savedEmployee.getClientId(), "Casual")
                .orElseThrow(() -> new RuntimeException("Casual leave type not found"));

        LeaveAllocation annualAllocation = new LeaveAllocation();
        annualAllocation.setClientId(savedEmployee.getClientId());
        annualAllocation.setEmployeeId(savedEmployee.getId());
        annualAllocation.setLeaveTypeId(annualLeaveType.getId());
        annualAllocation.setTotalDays(employeeType.getAnnualLeaveDays());
        annualAllocation.setUsedDays(0);
        annualAllocation.setYear(Year.now().getValue());
        leaveAllocationRepository.save(annualAllocation);

        LeaveAllocation casualAllocation = new LeaveAllocation();
        casualAllocation.setClientId(savedEmployee.getClientId());
        casualAllocation.setEmployeeId(savedEmployee.getId());
        casualAllocation.setLeaveTypeId(casualLeaveType.getId());
        casualAllocation.setTotalDays(employeeType.getCasualLeaveDays());
        casualAllocation.setUsedDays(0);
        casualAllocation.setYear(Year.now().getValue());
        leaveAllocationRepository.save(casualAllocation);

        return savedEmployee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getEmployeesByClientId(Long clientId) {
        return employeeRepository.findByClientId(clientId);
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        existing.setClientId(employee.getClientId());
        existing.setEmployeeTypeId(employee.getEmployeeTypeId());
        existing.setFirstName(employee.getFirstName());
        existing.setLastName(employee.getLastName());
        existing.setEmail(employee.getEmail());
        existing.setJoinDate(employee.getJoinDate());
        existing.setStatus(employee.getStatus());
        existing.setAddress(employee.getAddress());
        existing.setNic(employee.getNic());
        existing.setDob(employee.getDob());
        existing.setEmergencyContact(employee.getEmergencyContact());

        return employeeRepository.save(existing);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}