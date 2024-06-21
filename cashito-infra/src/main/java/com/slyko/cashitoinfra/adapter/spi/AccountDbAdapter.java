package com.slyko.cashitoinfra.adapter.spi;

import com.slyko.cashitoapplication.domain.Account;
import com.slyko.cashitoapplication.port.out.AccountsSecondaryPort;
import com.slyko.cashitoinfra.adapter.spi.entity.AccountEntity;
import com.slyko.cashitoinfra.adapter.spi.mapper.AccountMapper;
import com.slyko.cashitoinfra.adapter.spi.repository.AccountReactiveRepository;
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
                .map(AccountMapper::toApi);
    }

    @Override
    public Mono<Account> findAccountById(UUID accountId) {
        return accountReactiveRepository
                .findById(accountId)
                .map(AccountMapper::toApi);
    }

    @Override
    public Mono<Account> createAccount(Account account) {
        return accountReactiveRepository
                .save(AccountMapper.toDb(account))
                .map(AccountMapper::toApi);
    }
}
