package com.slyko.cashitoinfra.adapter.api.dto;

import com.slyko.cashitoapplication.domain.Product;
import com.slyko.cashitoapplication.domain.ProductType;

import java.math.BigDecimal;

public record ProductResponse(String name, ProductType productType, BigDecimal cost) {

    public static ProductResponse fromDomain(Product product) {
        return new ProductResponse(
            product.name(),
            product.type(),
            product.cost()
        );
    }
}
