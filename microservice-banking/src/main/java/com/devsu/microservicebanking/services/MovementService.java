package com.devsu.microservicebanking.services;

import com.devsu.microservicebanking.entities.Movement;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MovementService {
    List<Movement> getAllMovements();

    Optional<Movement> getMovementById(Long id);

    Movement createOrUpdateMovement(Movement movement);

    List<Map<String, Object>> getMovementClientAccountRecords(Long clientId, Date dateFrom, Date dateTo);
}
