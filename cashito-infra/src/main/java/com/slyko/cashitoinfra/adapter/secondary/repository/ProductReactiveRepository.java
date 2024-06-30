package com.slyko.cashitoinfra.adapter.secondary.repository;

import com.slyko.cashitodomain.model.ProductType;
import com.slyko.cashitoinfra.adapter.secondary.entity.ProductEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface ProductReactiveRepository extends ReactiveCrudRepository<ProductEntity, UUID> {

    Mono<ProductEntity> findById(UUID id);
    Flux<ProductEntity> findByProductType(ProductType productType);
    Flux<ProductEntity> findByDealId(UUID dealId);
    Mono<Integer> deleteAllByDealId(UUID dealId);
}
