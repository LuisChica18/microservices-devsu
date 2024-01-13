package com.devsu.microservicebanking.entities;

import com.devsu.microservicebanking.enums.MovementTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Movement extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "fecha", nullable = false)
    private LocalDateTime movementDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimiento", nullable = false)
    private MovementTypeEnum movementType;

    @Column(name = "valor", nullable = false)
    private BigDecimal amount;

    @Column(name = "saldo", nullable = false)
    private BigDecimal balance;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Account.class)
    @JoinColumn(name = "cuenta_id", referencedColumnName = "id")
    private Account account;
}
