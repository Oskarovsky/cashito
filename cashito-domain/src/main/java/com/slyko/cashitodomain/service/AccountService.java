package com.slyko.cashitodomain.service;

import com.slyko.cashitodomain.port.in.AccountManagementPort;
import com.slyko.cashitodomain.port.out.AccountsSecondaryPort;
import com.slyko.cashitodomain.model.Account;
import com.slyko.cashitodomain.util.UseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class AccountService implements AccountManagementPort {

    private final AccountsSecondaryPort accountsSecondaryPort;

    @Override
    public Flux<Account> getAccounts() {
        return accountsSecondaryPort.getAll();
    }

    @Override
    public Mono<Account> getAccountById(UUID id, Long version, boolean loadRelations) {
        return accountsSecondaryPort.getById(id, version, loadRelations);
    }

    @Override
    public Mono<Account> createAccount(Account account) {
        return accountsSecondaryPort.createAccount(account);
    }

    @Override
    public Mono<Void> deleteAccountById(UUID id, Long version) {
        return accountsSecondaryPort.deleteAccountById(id, version);
    }


}
