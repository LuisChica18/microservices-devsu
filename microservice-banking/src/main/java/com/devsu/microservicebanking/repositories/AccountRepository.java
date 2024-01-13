package com.devsu.microservicebanking.repositories;

import com.devsu.microservicebanking.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
