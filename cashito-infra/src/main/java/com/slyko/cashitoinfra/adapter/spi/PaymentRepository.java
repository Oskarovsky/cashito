package com.slyko.cashitoinfra.adapter.spi;

import com.slyko.cashitoinfra.adapter.spi.entity.PaymentEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

//public abstract interface PaymentRepository extends ReactiveCrudRepository<PaymentEntity, UUID> {
//
//    Mono<PaymentEntity> findById(UUID id);
//}
