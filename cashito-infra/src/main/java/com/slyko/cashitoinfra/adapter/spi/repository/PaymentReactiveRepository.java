package com.slyko.cashitoinfra.adapter.spi.repository;

import com.slyko.cashitoinfra.adapter.spi.entity.PaymentEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public abstract interface PaymentReactiveRepository extends ReactiveCrudRepository<PaymentEntity, UUID> {

    Mono<PaymentEntity> findById(UUID id);
}
