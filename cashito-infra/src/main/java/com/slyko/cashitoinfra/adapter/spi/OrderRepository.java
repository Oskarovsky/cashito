package com.slyko.cashitoinfra.adapter.spi;

import com.slyko.cashitoinfra.adapter.spi.entity.OrderEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public abstract interface OrderRepository extends ReactiveCrudRepository<OrderEntity, UUID> {

    Mono<OrderEntity> findById(UUID id);

}
