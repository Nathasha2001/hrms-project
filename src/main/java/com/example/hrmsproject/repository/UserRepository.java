package com.example.hrmsproject.repository;

import com.example.hrmsproject.entity.User;
import com.example.hrmsproject.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);
    List<User> findByRole (UserRole role);
    List<User> findByClientId (Long clientId);

}
