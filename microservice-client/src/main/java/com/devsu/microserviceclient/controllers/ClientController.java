package com.devsu.microserviceclient.controllers;

import com.devsu.microserviceclient.entities.Client;
import com.devsu.microserviceclient.exceptions.ResourceNotFoundException;
import com.devsu.microserviceclient.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAll();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        return clientService.getClientById(id)
                .map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con el ID : " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.getClientById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con el ID : " + id));

        clientService.deleteClientById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/create")
    public ResponseEntity<Client> createClientFromDTO(@RequestBody Client personaClienteDTO) {
        Client cliente = clientService.createClient(personaClienteDTO);
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Client> updateClientById(@PathVariable Long id, @RequestBody Client personaClienteDTO) {

        Client client = clientService.getClientById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con el ID : " + id));

        Client cliente = clientService.updateClientById(client, personaClienteDTO);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }
}
