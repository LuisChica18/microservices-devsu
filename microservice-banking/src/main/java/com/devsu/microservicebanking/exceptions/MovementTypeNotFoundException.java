package com.devsu.microservicebanking.exceptions;

public class MovementTypeNotFoundException  extends RuntimeException {
    public MovementTypeNotFoundException(String message) {
        super(message);
    }
}
