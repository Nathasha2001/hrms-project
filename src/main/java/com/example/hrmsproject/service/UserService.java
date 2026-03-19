package com.example.hrmsproject.service;

import com.example.hrmsproject.dto.LoginRequestDto;
import com.example.hrmsproject.dto.LoginResponseDto;
import com.example.hrmsproject.entity.User;
import com.example.hrmsproject.entity.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);
    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    Optional<User> getUserByUsername(String username);

    List<User> getUsersByRole(UserRole role);

    List<User> getUsersByClientId(Long clientId);

    User updateUser(Long id, User user);

    void deleteUser(Long id);

    LoginResponseDto login(LoginRequestDto loginRequestDto);
}


