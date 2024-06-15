package com.slyko.cashitoinfra.adapter.spi;

import com.slyko.cashitoapplication.domain.Account;
import com.slyko.cashitoapplication.port.out.AccountsSecondaryPort;
import com.slyko.cashitoinfra.adapter.spi.entity.AccountEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AccountDbAdapter implements AccountsSecondaryPort {

    private final AccountReactiveRepository accountReactiveRepository;

    @Override
    public Flux<Account> findAllAccounts() {
        return accountReactiveRepository
                .findAll()
                .map(AccountEntity::toApi);
    }

    @Override
    public Mono<Account> findAccountById(UUID accountId) {
        return accountReactiveRepository
                .findById(accountId)
                .map(AccountEntity::toApi);
    }

    @Override
    public Mono<Account> createAccount(Account account) {
        return accountReactiveRepository
                .save(AccountEntity.toDb(account))
                .map(AccountEntity::toApi);
    }
}
