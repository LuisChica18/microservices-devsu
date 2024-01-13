package com.devsu.microservicebanking.services;

import com.devsu.microservicebanking.entities.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<Account> getAllAccounts();

    Optional<Account> getAccountById(Long id);

    Account createOrUpdateAccount(Account account);

    void deleteAccountById(Long id);
}
