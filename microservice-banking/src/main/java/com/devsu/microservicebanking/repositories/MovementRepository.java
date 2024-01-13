package com.devsu.microservicebanking.repositories;

import com.devsu.microservicebanking.entities.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface MovementRepository extends JpaRepository<Movement, Long> {
    @Query("SELECT m FROM Movement m WHERE m.movementDate = (SELECT MAX(m2.movementDate) FROM Movement m2)")
    Movement findLastMovement();

    @Query(value = "SELECT movimientos.fecha AS Fecha, persona.nombre AS Cliente, " +
            "cuenta.numero_cuenta AS 'Numero Cuenta', cuenta.tipo_cuenta AS Tipo, " +
            "cuenta.saldo_inicial AS 'Saldo Inicial', cuenta.estado AS Estado, " +
            "CASE WHEN movimientos.tipo_movimiento = 'Retiro' THEN -1 * movimientos.valor ELSE movimientos.valor END AS Movimiento, " +
            "movimientos.saldo AS 'Saldo Disponible' " +
            "FROM movimientos " +
            "INNER JOIN cuenta ON movimientos.cuenta_id = cuenta.id " +
            "INNER JOIN cliente ON cuenta.cliente_id = cliente.id " +
            "INNER JOIN persona ON cliente.id = persona.id " +
            "WHERE cliente.id = :clientId " +
            "AND (DATE_FORMAT(movimientos.fecha, '%Y-%m-%d') BETWEEN DATE_FORMAT(:dateFrom, '%Y-%m-%d') AND DATE_FORMAT(:dateTo, '%Y-%m-%d')) " +
            "ORDER BY movimientos.fecha DESC", nativeQuery = true)
    List<Map<String, Object>> getMovementClientAccountRecords(Long clientId, Date dateFrom, Date dateTo);
}
