package com.example.hrmsproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmergencyContactDto {
    private Long id;
    private String name;
    private String relationship;
    private String phone;
}