package com.example.hrmsproject.dto;

import com.example.hrmsproject.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDto {
    private long id;
    private String username;
    private UserRole role;
    private Long clientId;
    private String message;


}
