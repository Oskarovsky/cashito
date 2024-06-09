package com.slyko.cashitoinfra.adapter.api.dto;

import com.slyko.cashitoapplication.domain.Product;
import com.slyko.cashitoapplication.domain.ProductType;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(UUID id, String name, ProductType productType, BigDecimal cost) {

    public static ProductResponse fromDomain(Product product) {
        return new ProductResponse(
            product.id(),
            product.name(),
            product.type(),
            product.cost()
        );
    }
}
