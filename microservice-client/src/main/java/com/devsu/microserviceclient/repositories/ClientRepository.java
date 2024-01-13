package com.devsu.microserviceclient.repositories;

import com.devsu.microserviceclient.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
