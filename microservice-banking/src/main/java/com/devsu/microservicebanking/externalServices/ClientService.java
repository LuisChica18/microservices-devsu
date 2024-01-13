package com.devsu.microservicebanking.externalServices;

import com.devsu.microserviceclient.entities.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CLIENT-SERVICE")
public interface ClientService {

    @GetMapping("/clientes/{id}")
    Client getClient(@PathVariable Long id);
}
