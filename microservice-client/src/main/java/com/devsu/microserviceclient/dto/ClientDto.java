package com.devsu.microserviceclient.dto;

import lombok.Data;

@Data
public class ClientDto {
    private String name;
    private String gender;
    private Integer age;
    private String dni;
    private String address;
    private String phone;
    private String password;
    private Boolean status;
}
