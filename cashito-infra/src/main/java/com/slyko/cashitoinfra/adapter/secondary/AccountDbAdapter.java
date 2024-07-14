package com.slyko.cashitoinfra.adapter.secondary;

import com.slyko.cashitoapplication.exception.DealNotFoundException;
import com.slyko.cashitoapplication.exception.UnexpectedDealVersionException;
import com.slyko.cashitodomain.model.Account;
import com.slyko.cashitodomain.port.out.AccountsSecondaryPort;
import com.slyko.cashitoinfra.adapter.secondary.mapper.AccountMapper;
import com.slyko.cashitoinfra.adapter.secondary.repository.AccountReactiveRepository;
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
    public Flux<Account> findAll() {
        return accountReactiveRepository
                .findAll()
                .map(AccountMapper::toApi);
    }

    @Override
    public Mono<Account> findById(UUID accountId, Long version, boolean loadRelations) {
        final Mono<Account> dealMono = accountReactiveRepository.findById(accountId)
                .switchIfEmpty(Mono.error(new DealNotFoundException(accountId)))
                .handle((account, sink) -> {
                    // Optimistic locking: pre-check
                    if (version != null && !version.equals(account.getVersion())) {
                        // The version are different, return an error
                        sink.error(new UnexpectedDealVersionException(version, account.getVersion()));
                    } else {
                        Account api = AccountMapper.toApi(account);
                        sink.next(api);
                    }
                });
        // Load the related objects, if requested
        return loadRelations
                ? dealMono // TODO add loadRelations
                : dealMono;
    }

    @Override
    public Mono<Account> create(Account account) {
        return accountReactiveRepository
                .save(AccountMapper.toDb(account))
                .map(AccountMapper::toApi);
    }

    @Override
    public Mono<Account> update(UUID uuid, Long version, Account account) {
        return null;
    }

    @Override
    public Mono<Void> deleteById(UUID uuid, Long version) {
        // TODO add function for removing account id and all object which has relation to account
        return null;    }
}
