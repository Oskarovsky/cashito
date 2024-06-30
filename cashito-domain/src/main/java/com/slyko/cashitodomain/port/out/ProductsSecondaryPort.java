package com.slyko.cashitodomain.port.out;

import com.slyko.cashitodomain.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public abstract interface ProductsSecondaryPort {

    Flux<Product> findAllProducts();
    Mono<Product> findProductById(UUID productId);
    Mono<Product> createProduct(Product product);
    Mono<Void> deleteProduct(UUID productId);
}
