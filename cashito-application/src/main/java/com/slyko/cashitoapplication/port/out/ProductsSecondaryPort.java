package com.slyko.cashitoapplication.port.out;

import com.slyko.cashitodomain.domain.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public abstract interface ProductsSecondaryPort {

    Flux<Product> findAllProducts();
    Mono<Product> findProductById(UUID productId);
    Mono<Product> createProduct(Product product);
}
