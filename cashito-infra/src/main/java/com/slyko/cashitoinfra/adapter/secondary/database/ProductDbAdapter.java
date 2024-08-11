package com.slyko.cashitoinfra.adapter.secondary.database;

import com.slyko.cashitodomain.port.out.ProductsSecondaryPort;
import com.slyko.cashitodomain.model.Product;
import com.slyko.cashitoinfra.adapter.secondary.database.mapper.ProductMapper;
import com.slyko.cashitoinfra.adapter.secondary.database.repository.ProductReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductDbAdapter implements ProductsSecondaryPort {

    private final ProductReactiveRepository productReactiveRepository;

    @Override
    public Flux<Product> findAll() {
        return productReactiveRepository
                .findAll()
                .map(ProductMapper::toApi);
    }

    @Override
    public Mono<Product> findById(UUID id, Long version, boolean loadRelations) {
        return productReactiveRepository
                .findById(id)
                .map(ProductMapper::toApi);
    }

    @Override
    public Mono<Product> create(Product product) {
        return productReactiveRepository
                .save(ProductMapper.toDb(product))
                .map(ProductMapper::toApi);
    }

    @Override
    public Mono<Product> update(UUID uuid, Long version, Product product) {
        return null;
    }

    @Override
    public Mono<Void> deleteById(UUID uuid, Long version) {
        return productReactiveRepository
                .deleteById(uuid);
    }

    @Override
    public Flux<Product> getDealProducts(UUID dealId) {
        return productReactiveRepository
                .findByDealId(dealId)
                .map(ProductMapper::toApi);
    }
}
