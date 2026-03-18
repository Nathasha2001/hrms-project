package com.example.hrmsproject.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "leave_types")
@Data
public class LeaveType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id")
    private Long clientId;

    private String name;

}
