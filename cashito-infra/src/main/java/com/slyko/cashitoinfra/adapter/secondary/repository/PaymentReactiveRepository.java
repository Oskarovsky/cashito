package com.slyko.cashitoinfra.adapter.secondary.repository;

import com.slyko.cashitoinfra.adapter.secondary.entity.PaymentEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public abstract interface PaymentReactiveRepository extends ReactiveCrudRepository<PaymentEntity, UUID> {

    Flux<PaymentEntity> findByDealId(UUID id);
}
