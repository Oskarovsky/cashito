package com.slyko.cashitoinfra.adapter.spi;

import com.slyko.cashitoinfra.adapter.spi.entity.AccountEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public abstract interface AccountReactiveRepository extends ReactiveCrudRepository<AccountEntity, UUID> {
}
