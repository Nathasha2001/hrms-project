package com.example.hrmsproject.repository;

import com.example.hrmsproject.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {

}
