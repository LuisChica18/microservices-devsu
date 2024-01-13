package com.devsu.microserviceclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceClientApplication.class, args);
    }

}
