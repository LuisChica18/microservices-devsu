package com.devsu.microserviceclient.controllers;

import com.devsu.microserviceclient.entities.Client;
import com.devsu.microserviceclient.services.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private ClientService clientService;

    ObjectMapper objectMapper;

    Client client = new Client();

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();

        client.setId(1L);
        client.setName("Pepe Pedroza");
        client.setPassword("pepepepe");
        client.setPhone("5936633636");
        client.setAddress("Av. Camarata");
        client.setGender("Masculino");
        client.setAge(44);
    }

    @Test
    public void TestFind() throws Exception {

        //Given
        when(clientService.getClientById(1L)).thenReturn(Optional.ofNullable(client));

        // When
        mvc.perform(get("/clientes/1").contentType(MediaType.APPLICATION_JSON))
                // Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Pepe Pedroza"))
                .andExpect(jsonPath("$.password").value("pepepepe"));

        verify(clientService).getClientById(1L);
    }


    @Test
    void testList() throws Exception {

        Client otherClient = new Client();
        otherClient.setId(2L);
        otherClient.setName("Juana Juarez");
        otherClient.setPassword("jujujaja");
        otherClient.setPhone("555555555");
        otherClient.setAddress("Av. Camarata");
        otherClient.setGender("Masculino");
        otherClient.setAge(44);

        // Given
        List<Client> clients = Arrays.asList(client, otherClient);
        when(clientService.getAll()).thenReturn(clients);

        // When
        mvc.perform(get("/clientes").contentType(MediaType.APPLICATION_JSON))
                // Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Pepe Pedroza"))
                .andExpect(jsonPath("$[1].name").value("Juana Juarez"))
                .andExpect(jsonPath("$[0].password").value("pepepepe"))
                .andExpect(jsonPath("$[1].phone").value("555555555"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(content().json(objectMapper.writeValueAsString(clients)));

        verify(clientService).getAll();
    }

    @Test
    void testSave() throws Exception {
        // Given
        when(clientService.createClient(any())).then(invocation ->{
            Client c = invocation.getArgument(0);
            c.setId(3L);
            return c;
        });

        // when
        mvc.perform(post("/clientes/create").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client)))
                // Then
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.name", is("Pepe Pedroza")))
                .andExpect(jsonPath("$.gender", is("Masculino")));
        verify(clientService).createClient(any());

    }
}
