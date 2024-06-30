package com.slyko.cashitodomain.port.out;

import com.slyko.cashitodomain.model.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public abstract interface AccountsSecondaryPort {

    Flux<Account> getAll();
    Mono<Account> getById(UUID productId, Long version, boolean loadRelations);
    Mono<Account> createAccount(Account account);
    Mono<Void> deleteAccountById(UUID id, Long version);
}
