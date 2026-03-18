package com.example.hrmsproject.service;

import com.example.hrmsproject.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Client saveClient(Client client);
    List<Client> getAllClients();
    Optional<Client> getClientById(Long id);
}