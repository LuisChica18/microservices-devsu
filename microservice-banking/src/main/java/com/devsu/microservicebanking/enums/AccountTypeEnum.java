package com.devsu.microservicebanking.enums;

public enum AccountTypeEnum {
    AHORRO("ahorros"),
    CORRIENTE("corriente");

    private String typeAccount;

    AccountTypeEnum(String typeAccount) {
        this.typeAccount = typeAccount;
    }
}
