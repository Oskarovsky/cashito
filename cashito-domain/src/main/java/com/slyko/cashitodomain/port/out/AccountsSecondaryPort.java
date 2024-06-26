package com.slyko.cashitodomain.port.out;

import com.slyko.cashitodomain.model.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public abstract interface AccountsSecondaryPort {

    Flux<Account> findAllAccounts();
    Mono<Account> findAccountById(UUID productId);
    Mono<Account> createAccount(Account account);
}
