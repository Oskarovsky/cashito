package com.slyko.cashitoinfra.adapter.secondary.database.repository;

import com.slyko.cashitoinfra.adapter.secondary.database.entity.AccountEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public abstract interface AccountReactiveRepository extends ReactiveCrudRepository<AccountEntity, UUID> {

    Mono<AccountEntity> findByName(String name);
}
