package com.devsu.microservicebanking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EntityScan({"com.devsu.microserviceclient.entities",
            "com.devsu.microservicebanking.entities"})
@EnableDiscoveryClient
@EnableFeignClients
public class MicroserviceBankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceBankingApplication.class, args);
    }

}
