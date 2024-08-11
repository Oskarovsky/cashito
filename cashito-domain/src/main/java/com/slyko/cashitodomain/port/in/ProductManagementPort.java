package com.slyko.cashitodomain.port.in;

import com.slyko.cashitodomain.model.Product;
import reactor.core.publisher.Flux;

import java.util.UUID;

public abstract interface ProductManagementPort extends BaseManagementPort<Product, UUID> {

    Flux<Product> getDealProducts(UUID dealId);
}
