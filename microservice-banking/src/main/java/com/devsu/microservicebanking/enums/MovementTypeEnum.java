package com.devsu.microservicebanking.enums;

public enum MovementTypeEnum {

    DEPOSITO("credito"),
    RETIRO("debito");

    private String typeMovement;

    MovementTypeEnum(String typeMovement) {
        this.typeMovement = typeMovement;
    }
}
