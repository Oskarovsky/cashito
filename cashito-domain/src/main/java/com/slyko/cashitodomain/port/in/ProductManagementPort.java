package com.slyko.cashitodomain.port.in;

import com.slyko.cashitodomain.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public abstract interface ProductManagementPort {

    Flux<Product> getProducts();
    Mono<Product> getProduct(UUID id);
    Mono<Product> createProduct(Product product);
}
