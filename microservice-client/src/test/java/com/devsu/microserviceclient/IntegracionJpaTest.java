package com.devsu.microserviceclient;

import com.devsu.microserviceclient.entities.Client;
import com.devsu.microserviceclient.repositories.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class IntegracionJpaTest {
    @Autowired
    ClientRepository repository;

    @Test
    void testSave() {

        // Given
        Client cliente = new Client();
        cliente.setId(1L);
        cliente.setName("Pepe Pedroza");
        cliente.setPassword("pepepepe");
        cliente.setPhone("5936633636");
        cliente.setAddress("Av. Camarata");
        cliente.setGender("Masculino");
        cliente.setAge(44);

        // When
        Client client = repository.save(cliente);

        // Then
        assertEquals("pepepepe", client.getPassword());
        assertEquals(1, client.getId());
    }

    @Test
    void testFindById() {
        Optional<Client> client = repository.findById(1L);
        assertTrue(client.isPresent());
        assertEquals("Coco Cocodrilo", client.orElseThrow().getName());
    }

    @Test
    void testFindAll() {
        List<Client> client = repository.findAll();
        assertFalse(client.isEmpty());
        assertEquals(1, client.size());
    }

    @Test
    void testFindByIdThrowException() {
        Optional<Client> client = repository.findById(9999L);
        assertThrows(NoSuchElementException.class, client::orElseThrow);
        assertFalse(client.isPresent());
    }

    @Test
    void testUpdate() {

        // Given
        Client client = new Client();
        client.setId(1L);
        client.setName("Pepe Pedroza");
        client.setPassword("pepepepe");
        client.setPhone("5936633636");
        client.setAddress("Av. Camarata");
        client.setGender("Masculino");
        client.setAge(44);

        // When
        Client clientSaved = repository.save(client);

        // Then
        assertEquals("pepepepe", client.getPassword());
        assertEquals(1, client.getId());

        // When
        clientSaved.setAge(34);
        clientSaved.setName("Juana");
        Client clientUpdated = repository.save(clientSaved);

        // Then
        assertEquals("Juana", clientUpdated.getName());
        assertEquals(34, clientUpdated.getAge());
    }

    @Test
    void testDelete() {
        Client client = repository.findById(1L).orElseThrow();
        assertEquals("Coco Cocodrilo", client.getName());

        repository.delete(client);

        assertThrows(NoSuchElementException.class, () -> {
            repository.findById(2L).orElseThrow();
        });
        assertEquals(0, repository.findAll().size());
    }
}
