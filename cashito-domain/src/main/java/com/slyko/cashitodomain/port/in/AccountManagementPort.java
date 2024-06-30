package com.slyko.cashitodomain.port.in;

import com.slyko.cashitodomain.model.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public abstract interface AccountManagementPort {

    Flux<Account> getAccounts();
    Mono<Account> getAccountById(UUID id, Long version, boolean loadRelations);
    Mono<Account> createAccount(Account account);
    Mono<Void> deleteAccountById(UUID id, Long version);
}
