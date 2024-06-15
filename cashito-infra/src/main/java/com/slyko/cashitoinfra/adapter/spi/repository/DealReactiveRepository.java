package com.slyko.cashitoinfra.adapter.spi.repository;

import com.slyko.cashitoapplication.domain.Status;
import com.slyko.cashitoinfra.adapter.spi.entity.DealEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public abstract interface DealReactiveRepository extends ReactiveCrudRepository<DealEntity, UUID> {

    Mono<DealEntity> findById(UUID id);

    Flux<DealEntity> findAllByStatus(Status status);

}
