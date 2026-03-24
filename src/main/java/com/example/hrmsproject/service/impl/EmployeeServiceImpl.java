package com.example.hrmsproject.service.impl;

import com.example.hrmsproject.dto.EmployeeProfileDto;
import com.example.hrmsproject.dto.EmergencyContactDto;
import com.example.hrmsproject.dto.LeaveBalanceDto;
import com.example.hrmsproject.entity.Department;
import com.example.hrmsproject.entity.Employee;
import com.example.hrmsproject.entity.EmployeeType;
import com.example.hrmsproject.entity.LeaveAllocation;
import com.example.hrmsproject.entity.LeaveType;
import com.example.hrmsproject.repository.DepartmentRepository;
import com.example.hrmsproject.repository.EmployeeEmergencyContactRepository;
import com.example.hrmsproject.repository.EmployeeRepository;
import com.example.hrmsproject.repository.EmployeeTypeRepository;
import com.example.hrmsproject.repository.LeaveAllocationRepository;
import com.example.hrmsproject.repository.LeaveTypeRepository;
import com.example.hrmsproject.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeTypeRepository employeeTypeRepository;
    private final LeaveTypeRepository leaveTypeRepository;
    private final LeaveAllocationRepository leaveAllocationRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeEmergencyContactRepository emergencyContactRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               EmployeeTypeRepository employeeTypeRepository,
                               LeaveTypeRepository leaveTypeRepository,
                               LeaveAllocationRepository leaveAllocationRepository,
                               DepartmentRepository departmentRepository,
                               EmployeeEmergencyContactRepository emergencyContactRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeTypeRepository = employeeTypeRepository;
        this.leaveTypeRepository = leaveTypeRepository;
        this.leaveAllocationRepository = leaveAllocationRepository;
        this.departmentRepository = departmentRepository;
        this.emergencyContactRepository = emergencyContactRepository;
    }

    @Override
    @Transactional
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

        LeaveType sickLeaveType = leaveTypeRepository
                .findByClientIdAndNameIgnoreCase(savedEmployee.getClientId(), "Sick")
                .orElseThrow(() -> new RuntimeException("Sick leave type not found"));

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

        LeaveAllocation sickAllocation = new LeaveAllocation();
        sickAllocation.setClientId(savedEmployee.getClientId());
        sickAllocation.setEmployeeId(savedEmployee.getId());
        sickAllocation.setLeaveTypeId(sickLeaveType.getId());
        sickAllocation.setTotalDays(employeeType.getSickLeaveDays());
        sickAllocation.setUsedDays(0);
        sickAllocation.setYear(Year.now().getValue());
        leaveAllocationRepository.save(sickAllocation);

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
        existing.setDepartmentId(employee.getDepartmentId());
        existing.setFirstName(employee.getFirstName());
        existing.setLastName(employee.getLastName());
        existing.setEmail(employee.getEmail());
        existing.setPhone(employee.getPhone());
        existing.setJoinDate(employee.getJoinDate());
        existing.setStatus(employee.getStatus());
        existing.setAddress(employee.getAddress());
        existing.setNic(employee.getNic());
        existing.setDob(employee.getDob());
        existing.setRole(employee.getRole());

        return employeeRepository.save(existing);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeProfileDto getEmployeeProfile(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        EmployeeType employeeType = employeeTypeRepository.findById(employee.getEmployeeTypeId()).orElse(null);

        Department department = employee.getDepartmentId() != null
                ? departmentRepository.findById(employee.getDepartmentId()).orElse(null)
                : null;

        List<EmergencyContactDto> contacts = emergencyContactRepository.findByEmployeeId(employeeId)
                .stream()
                .map(c -> new EmergencyContactDto(
                        c.getId(),
                        c.getName(),
                        c.getPhone()
                ))
                .toList();

        List<LeaveBalanceDto> leaveBalances = leaveAllocationRepository.findByEmployeeId(employeeId)
                .stream()
                .map(a -> {
                    LeaveType lt = leaveTypeRepository.findById(a.getLeaveTypeId()).orElse(null);
                    int remaining = a.getTotalDays() - a.getUsedDays();

                    return new LeaveBalanceDto(
                            a.getLeaveTypeId(),
                            lt != null ? lt.getName() : "Unknown",
                            a.getTotalDays(),
                            a.getUsedDays(),
                            remaining
                    );
                })
                .toList();

        return EmployeeProfileDto.builder()
                .id(employee.getId())
                .clientId(employee.getClientId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .joinDate(employee.getJoinDate())
                .status(employee.getStatus())
                .address(employee.getAddress())
                .nic(employee.getNic())
                .dob(employee.getDob())
                .role(employee.getRole())
                .employeeTypeName(employeeType != null ? employeeType.getName() : null)
                .departmentName(department != null ? department.getName() : null)
                .emergencyContacts(contacts)
                .leaveBalances(leaveBalances)
                .build();
    }
}