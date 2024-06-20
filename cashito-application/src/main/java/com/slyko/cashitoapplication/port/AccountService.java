package com.slyko.cashitoapplication.port;

import com.slyko.cashitoapplication.domain.Account;
import com.slyko.cashitoapplication.port.in.AccountManagementPort;
import com.slyko.cashitoapplication.port.out.AccountsSecondaryPort;
import com.slyko.cashitoapplication.util.UseCase;
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
        return accountsSecondaryPort.findAllAccounts();
    }

    @Override
    public Mono<Account> getAccount(UUID id) {
        return null;
    }

    @Override
    public Mono<Account> createAccount(Account account) {
        return accountsSecondaryPort.createAccount(account);
    }
}
