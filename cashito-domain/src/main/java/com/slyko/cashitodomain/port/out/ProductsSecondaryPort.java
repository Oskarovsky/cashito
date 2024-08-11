package com.slyko.cashitodomain.port.out;

import com.slyko.cashitodomain.model.Product;
import reactor.core.publisher.Flux;

import java.util.UUID;

public abstract interface ProductsSecondaryPort extends BaseSecondaryPort<Product, UUID> {

    Flux<Product> getDealProducts(UUID dealId);

}
