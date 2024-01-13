package com.devsu.microservicebanking;

import com.devsu.microservicebanking.entities.Account;
import com.devsu.microservicebanking.enums.AccountTypeEnum;
import com.devsu.microserviceclient.entities.Client;

import java.math.BigDecimal;

public class DataSeed {

    public static final Client client001 = new Client("xxxxx", true);

    public static final Account account001 = new Account(1L, "21221", AccountTypeEnum.AHORRO, new BigDecimal(2500), true, client001);
    public static final Account account002 = new Account(2L, "21222", AccountTypeEnum.AHORRO, new BigDecimal(3200), true, client001);
}
