package com.slyko.cashitoinfra.adapter.spi;

import com.slyko.cashitoapplication.domain.Product;
import com.slyko.cashitoapplication.domain.ProductType;
import com.slyko.cashitoinfra.adapter.spi.entity.ProductEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface ProductReactiveRepository extends ReactiveCrudRepository<ProductEntity, UUID> {

    Mono<ProductEntity> findById(UUID id);

    Flux<ProductEntity> findByProductType(ProductType productType);
}
