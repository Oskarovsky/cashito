package com.slyko.cashitoinfra.adapter.spi;

import com.slyko.cashitoapplication.domain.Product;
import com.slyko.cashitoapplication.port.out.ProductsSecondaryPort;
import com.slyko.cashitoinfra.adapter.spi.entity.ProductEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class ProductDbAdapter implements ProductsSecondaryPort {

    private final ProductReactiveRepository productReactiveRepository;

    public ProductDbAdapter(ProductReactiveRepository productReactiveRepository) {
        this.productReactiveRepository = productReactiveRepository;
    }

    @Override
    public Mono<Product> findProductById(UUID id) {
        return productReactiveRepository
                .findById(id)
                .map(ProductEntity::toApi);
    }

    @Override
    public Mono<Product> createProduct(Product product) {
        return productReactiveRepository
                .save(ProductEntity.toDb(product))
                .map(ProductEntity::toApi);
    }
}
