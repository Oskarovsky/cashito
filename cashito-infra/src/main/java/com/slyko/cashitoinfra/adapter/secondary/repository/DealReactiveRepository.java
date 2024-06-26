package com.slyko.cashitoinfra.adapter.secondary.repository;

import com.slyko.cashitodomain.model.Status;
import com.slyko.cashitoinfra.adapter.secondary.entity.DealEntity;
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
