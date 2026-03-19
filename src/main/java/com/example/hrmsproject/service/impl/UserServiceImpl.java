package com.example.hrmsproject.service.impl;

import com.example.hrmsproject.entity.User;
import com.example.hrmsproject.entity.UserRole;
import com.example.hrmsproject.repository.ClientRepository;
import com.example.hrmsproject.repository.UserRepository;
import com.example.hrmsproject.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;

    public UserServiceImpl(UserRepository userRepository, ClientRepository clientRepository) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public User createUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        if (user.getRole() == UserRole.EMPLOYER) {
            if (user.getClientId() == null) {
                throw new RuntimeException("Employer must have a clientId");
            }

            clientRepository.findById(user.getClientId())
                    .orElseThrow(() -> new RuntimeException("Client not found"));
        }

        if (user.getRole() == UserRole.SYSTEM_ADMIN) {
            user.setClientId(null);
        }

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsersByRole(UserRole role) {
        return userRepository.findByRole(role);
    }

    @Override
    public List<User> getUsersByClientId(Long clientId) {
        return userRepository.findByClientId(clientId);
    }

    @Override
    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setRole(user.getRole());

        if (user.getRole() == UserRole.EMPLOYER) {
            if (user.getClientId() == null) {
                throw new RuntimeException("Employer must have a clientId");
            }

            clientRepository.findById(user.getClientId())
                    .orElseThrow(() -> new RuntimeException("Client not found"));

            existingUser.setClientId(user.getClientId());
        } else {
            existingUser.setClientId(null);
        }

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}