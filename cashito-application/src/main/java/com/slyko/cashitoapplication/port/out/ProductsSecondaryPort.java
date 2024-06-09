package com.slyko.cashitoapplication.port.out;

import com.slyko.cashitoapplication.domain.Product;
import reactor.core.publisher.Mono;

import java.util.UUID;

public abstract interface ProductsSecondaryPort {

    Mono<Product> findProductById(UUID productId);
    Mono<Product> createProduct(Product product);
}
