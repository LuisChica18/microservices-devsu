package com.devsu.microserviceclient.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "persona")
@Inheritance(strategy = InheritanceType.JOINED)
//@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "genero")
    private String gender;

    @Column(name = "edad")
    private int age;

    @Column(name = "identificacion", unique = true)
    private String dni;

    @Column(name = "direccion")
    private String address;

    @Column(name = "telefono")
    private String phone;
}
