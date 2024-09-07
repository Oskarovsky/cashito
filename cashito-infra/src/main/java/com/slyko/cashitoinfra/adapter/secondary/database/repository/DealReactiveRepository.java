package com.slyko.cashitoinfra.adapter.secondary.database.repository;

import com.slyko.cashitodomain.model.DealStatus;
import com.slyko.cashitoinfra.adapter.secondary.database.entity.DealEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public abstract interface DealReactiveRepository extends ReactiveCrudRepository<DealEntity, UUID> {

    Mono<DealEntity> findById(UUID id);
    Flux<DealEntity> findByStatus(DealStatus dealStatus);

}
