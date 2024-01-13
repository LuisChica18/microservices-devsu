package com.devsu.microservicebanking.exceptions;

public class BalanceLessThanZeroException extends RuntimeException {
    public BalanceLessThanZeroException(String message) {
        super(message);
    }
}
