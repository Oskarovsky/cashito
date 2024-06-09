package com.slyko.cashitoapplication.port.in;

import com.slyko.cashitoapplication.domain.Product;
import reactor.core.publisher.Mono;

import java.util.UUID;

public abstract interface ProductManagementPort {

    Mono<Product> getProduct(UUID id);
    Mono<Product> createProduct(Product product);
}
