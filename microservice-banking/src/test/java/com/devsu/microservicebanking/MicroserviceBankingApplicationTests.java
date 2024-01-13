package com.devsu.microservicebanking;

import com.devsu.microservicebanking.entities.Account;
import com.devsu.microservicebanking.externalServices.ClientService;
import com.devsu.microservicebanking.repositories.AccountRepository;
import com.devsu.microservicebanking.repositories.MovementRepository;
import com.devsu.microservicebanking.services.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class MicroserviceBankingApplicationTests {

    @MockBean
    AccountRepository accountRepository;

    @MockBean
    MovementRepository movementRepository;

    @MockBean
    ClientService clientService;

    @Autowired
    AccountService accountService;


    @BeforeEach
    void setup(){
//        accountRepository = mock(AccountRepository.class);
//        movementRepository = mock(MovementRepository.class);
//        clientService = mock(ClientService.class);
//        accountService = new AccountServicesImpl(accountRepository, clientService);
    }

    @Test
    void contextLoads() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(DataSeed.account001));
        when(accountRepository.findById(2L)).thenReturn(Optional.of(DataSeed.account002));
        when(clientService.getClient(1L)).thenReturn(DataSeed.client001);

        Optional<Account> account = accountService.getAccountById(1L);
        assertEquals(new BigDecimal(2500), account.get().getInitialBalance());

        verify(accountRepository).findById(1L);
    }

}
