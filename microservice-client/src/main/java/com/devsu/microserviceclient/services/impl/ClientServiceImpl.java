package com.devsu.microserviceclient.services.impl;

import com.devsu.microserviceclient.entities.Client;
import com.devsu.microserviceclient.repositories.ClientRepository;
import com.devsu.microserviceclient.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client updateClientById(Client client, Client clienteUpdate) {
        client.setPassword(clienteUpdate.getPassword());
        client.setName(clienteUpdate.getName());
        client.setAddress(clienteUpdate.getAddress());
        client.setState(clienteUpdate.getState());
        client.setAge(clienteUpdate.getAge());
        client.setGender(clienteUpdate.getGender());
        client.setPhone(clienteUpdate.getPhone());
        return this.clientRepository.save(client);
    }

    @Override
    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }
}
