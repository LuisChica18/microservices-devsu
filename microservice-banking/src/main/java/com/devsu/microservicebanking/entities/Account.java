package com.devsu.microservicebanking.entities;

import com.devsu.microservicebanking.enums.AccountTypeEnum;
import com.devsu.microserviceclient.entities.Client;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "cuenta")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_cuenta", unique = true, nullable = false)
    private String accountNumber;

    @Column(name = "tipo_cuenta", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountTypeEnum accountType;

    @Column(name = "saldo_inicial", nullable = false)
    @Min(value = 1,message = "El saldo inicial debe ser mayor o igual 1")
    private BigDecimal initialBalance;

    @Column(name = "estado")
    private Boolean state;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Client.class)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Client client;
    
}
