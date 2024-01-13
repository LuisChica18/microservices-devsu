package com.devsu.microservicebanking.controllers;

import com.devsu.microservicebanking.entities.Movement;
import com.devsu.microservicebanking.services.MovementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/movimientos")
public class MovementController {

    private MovementService movementService;

    @Autowired
    public MovementController(MovementService movementService) {
        this.movementService = movementService;
    }

    @GetMapping
    public ResponseEntity<List<Movement>> getAllMovements() {
        List<Movement> movements = movementService.getAllMovements();
        return new ResponseEntity<>(movements, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movement> getMovementById(@PathVariable Long id) {
        return movementService.getMovementById(id)
                .map(movimiento -> new ResponseEntity<>(movimiento, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<Movement> createMovement(@RequestBody Movement movimientos) {
        Movement createMovement = movementService.createOrUpdateMovement(movimientos);
        return new ResponseEntity<>(createMovement, HttpStatus.CREATED);
    }

    @GetMapping("/reporte")
    public List<Map<String, Object>> movementsReport(
            @RequestParam Long clientId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFrom,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTo
    ) {
        return movementService.getMovementClientAccountRecords(clientId, dateFrom, dateTo);
    }
}
