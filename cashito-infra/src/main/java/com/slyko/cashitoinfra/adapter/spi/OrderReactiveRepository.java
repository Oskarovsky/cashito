package com.slyko.cashitoinfra.adapter.spi;

import com.slyko.cashitoapplication.domain.Status;
import com.slyko.cashitoinfra.adapter.spi.entity.OrderEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public abstract interface OrderReactiveRepository extends ReactiveCrudRepository<OrderEntity, UUID> {

    Mono<OrderEntity> findById(UUID id);

    Flux<OrderEntity> findAllByStatus(Status status);

}
