package com.spring.oauth2.springdemooauth2.repository;

import com.spring.oauth2.springdemooauth2.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {

    Optional<Account> findByUsername(final String username);
}
