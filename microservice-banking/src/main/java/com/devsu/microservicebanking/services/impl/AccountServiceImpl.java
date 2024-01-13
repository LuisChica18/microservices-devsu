package com.devsu.microservicebanking.services.impl;

import com.devsu.microservicebanking.entities.Account;
import com.devsu.microservicebanking.externalServices.ClientService;
import com.devsu.microservicebanking.repositories.AccountRepository;
import com.devsu.microservicebanking.services.AccountService;
import com.devsu.microserviceclient.entities.Client;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository repository;

    private ClientService clientService;

    @Autowired
    public AccountServiceImpl(AccountRepository repository, ClientService clientService) {
        this.repository = repository;
        this.clientService = clientService;
    }

    @Override
    public List<Account> getAllAccounts() {
        return repository.findAll();
    }

    @Override
    public Optional<Account> getAccountById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Account createOrUpdateAccount(Account account) throws FeignException {
        Client client = clientService.getClient(account.getClient().getId());
        account.setClient(client);
        return repository.save(account);
    }

    @Override
    public void deleteAccountById(Long id) {
        repository.deleteById(id);
    }
}
