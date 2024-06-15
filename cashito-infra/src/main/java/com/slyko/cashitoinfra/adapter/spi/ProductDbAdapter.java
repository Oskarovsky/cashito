package com.slyko.cashitoinfra.adapter.spi;

import com.slyko.cashitoapplication.domain.Product;
import com.slyko.cashitoapplication.port.out.ProductsSecondaryPort;
import com.slyko.cashitoinfra.adapter.spi.mapper.ProductMapper;
import com.slyko.cashitoinfra.adapter.spi.repository.ProductReactiveRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class ProductDbAdapter implements ProductsSecondaryPort {

    private final ProductReactiveRepository productReactiveRepository;

    public ProductDbAdapter(ProductReactiveRepository productReactiveRepository) {
        this.productReactiveRepository = productReactiveRepository;
    }


    @Override
    public Flux<Product> findAllProducts() {
        return productReactiveRepository
                .findAll()
                .map(ProductMapper::toApi);
    }

    @Override
    public Mono<Product> findProductById(UUID id) {
        return productReactiveRepository
                .findById(id)
                .map(ProductMapper::toApi);
    }

    @Override
    public Mono<Product> createProduct(Product product) {
        return productReactiveRepository
                .save(ProductMapper.toDb(product))
                .map(ProductMapper::toApi);
    }
}
