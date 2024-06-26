package com.slyko.cashitoapplication.port.in;

import com.slyko.cashitodomain.domain.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public abstract interface AccountManagementPort {

    Flux<Account> getAccounts();
    Mono<Account> getAccount(UUID id);
    Mono<Account> createAccount(Account account);
}
