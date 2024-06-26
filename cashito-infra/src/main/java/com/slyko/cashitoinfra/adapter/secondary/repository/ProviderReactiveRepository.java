package com.slyko.cashitoinfra.adapter.secondary.repository;

import com.slyko.cashitoinfra.adapter.secondary.entity.ProviderEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface ProviderReactiveRepository extends ReactiveCrudRepository<ProviderEntity, UUID> {

    Mono<ProviderEntity> findById(UUID id);
}
