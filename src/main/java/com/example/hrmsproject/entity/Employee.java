package com.example.hrmsproject.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table( name= "employees")
@Data
public class Employee {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="client_id")
    private Long clientId;

    @Column(name = "employee_type_id")
    private Long employeeTypeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name= "last_name")
    private String lastName;

    private String email;

    private String address;

    @Column(name= "join_date")
    private LocalDate joinDate;

    private String status;

    private String nic;

    private LocalDate dob;

    @Column(name = "emergency_contact")
    private String emergencyContact;

    @CreationTimestamp
    @Column (name ="created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name= "updated_at")
    private LocalDateTime updated_at;

}
