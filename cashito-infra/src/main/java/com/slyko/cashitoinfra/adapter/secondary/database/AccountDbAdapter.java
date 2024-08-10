package com.slyko.cashitoinfra.adapter.secondary.database;

import com.slyko.cashitoapplication.exception.AccountNotFoundException;
import com.slyko.cashitoapplication.exception.DealNotFoundException;
import com.slyko.cashitoapplication.exception.UnexpectedDealVersionException;
import com.slyko.cashitodomain.model.Account;
import com.slyko.cashitodomain.port.out.AccountsSecondaryPort;
import com.slyko.cashitoinfra.adapter.secondary.database.mapper.AccountMapper;
import com.slyko.cashitoinfra.adapter.secondary.database.repository.AccountReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AccountDbAdapter implements AccountsSecondaryPort {

    private final AccountReactiveRepository accountReactiveRepository;

    @Override
    public Flux<Account> findAll() {
        return accountReactiveRepository
                .findAll()
                .map(AccountMapper::toApi);
    }

    @Override
    public Mono<Account> findById(UUID accountId, Long version, boolean loadRelations) {
        final Mono<Account> accountMono = accountReactiveRepository.findById(accountId)
                .switchIfEmpty(Mono.error(new AccountNotFoundException(accountId)))
                .handle((account, sink) -> {
                    // Optimistic locking: pre-check
                    if (version != null && !version.equals(account.getVersion())) {
                        // The version is different, return an error
                        sink.error(new UnexpectedDealVersionException(version, account.getVersion()));
                    } else {
                        Account api = AccountMapper.toApi(account);
                        sink.next(api);
                    }
                });
        // Load the related objects, if requested
        return loadRelations
                ? accountMono // TODO add loadRelations
                : accountMono;
    }

    @Override
    public Mono<Account> create(Account account) {
        return accountReactiveRepository
                .save(AccountMapper.toDb(account))
                .map(AccountMapper::toApi);
    }

    @Override
    public Mono<Account> update(UUID id, Long version, Account accountApi) {
        if (accountApi.getId() == null || accountApi.getVersion() == null) {
            return Mono.error(new IllegalArgumentException("When updating an account, the id and the version must be provided"));
        }
        return findById(id, version, false)
                .switchIfEmpty(Mono.error(new DealNotFoundException(accountApi.getId())))
                .map(AccountMapper::toDb)
                .flatMap(db -> {
                    Optional.ofNullable(accountApi.getName()).ifPresent(db::setName);
                    Optional.ofNullable(accountApi.getType()).ifPresent(db::setType);
                    return accountReactiveRepository.save(db);
                })
                .map(AccountMapper::toApi);
    }

    @Override
    public Mono<Void> deleteById(UUID uuid, Long version) {
        // TODO add function for removing account id and all object which has relation to account
        return null;
    }

    @Override
    public Mono<Account> findByName(String accountName, boolean loadRelations) {
        final Mono<Account> accountMono = accountReactiveRepository.findByName(accountName)
                .switchIfEmpty(Mono.error(new AccountNotFoundException(accountName)))
                .handle((account, sink) -> {
                    Account api = AccountMapper.toApi(account);
                    sink.next(api);
                });
        // Load the related objects, if requested
        return loadRelations
                ? accountMono // TODO add loadRelations
                : accountMono;
    }
}
