package com.example.hrmsproject.dto;

import com.example.hrmsproject.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDto {

    private String token;
    private String username;
    private String role;
    private Long clientId;
    private String message;


}
