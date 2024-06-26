package com.slyko.cashitoinfra.adapter.secondary;

import com.slyko.cashitodomain.port.out.ProductsSecondaryPort;
import com.slyko.cashitodomain.model.Product;
import com.slyko.cashitoinfra.adapter.secondary.mapper.ProductMapper;
import com.slyko.cashitoinfra.adapter.secondary.repository.ProductReactiveRepository;
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
