package com.devsu.microserviceclient.services;

import com.devsu.microserviceclient.entities.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    List<Client> getAll();

    void deleteClientById(Long id);

    Client createClient(Client client);

    Client updateClientById(Client id, Client client);

    Optional<Client> getClientById(Long id);
}
