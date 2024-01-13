package com.devsu.microservicebanking.controllers;

import com.devsu.microservicebanking.entities.Account;
import com.devsu.microservicebanking.enums.AccountTypeEnum;
import com.devsu.microservicebanking.services.AccountService;
import com.devsu.microserviceclient.entities.Client;
import com.devsu.microserviceclient.exceptions.ResourceNotFoundException;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cuentas")
public class AccountController {

    private final AccountService accountService;
    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id)
                .map(cuenta -> new ResponseEntity<>(cuenta, HttpStatus.OK))
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con el ID : " + id));
    }

    @PostMapping("/create")
//    @CircuitBreaker(name = "createAccountBreaker", fallbackMethod = "createAccountFallback")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount;
        try {
            createdAccount = accountService.createOrUpdateAccount(account);
        }catch (FeignException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account account) {

        accountService.getAccountById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrado con el ID : " + id));

        account.setId(id);
        Account updatedAccount;
        try {
            updatedAccount = accountService.createOrUpdateAccount(account);
        }catch (FeignException e){
            throw new ResourceNotFoundException("Problemas con integración de Cliente ");
        }

        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {

        accountService.getAccountById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrado con el ID : " + id));

        accountService.deleteAccountById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Account> createAccountFallback(Account account, Exception exception){
        log.info("service not available: ",exception.getMessage());
        Client client = new Client();
        client.setPhone("99999999");
        client.setAge(99);
        client.setGender("99999999");
        client.setState(false);
        client.setPassword("XXXXXXX");
        client.setName("Jhon Doe");
        client.setAddress("earth");
        client.setDni("99999999");
        client.setId(99999L);

        Account usuario = Account.builder()
                .id(9999L)
                .accountNumber("99999999")
                .accountType(AccountTypeEnum.AHORRO)
                .initialBalance(BigDecimal.ZERO)
                .state(false)
                .client(client)
                .build();
        return new ResponseEntity<>(usuario,HttpStatus.OK);
    }
}
