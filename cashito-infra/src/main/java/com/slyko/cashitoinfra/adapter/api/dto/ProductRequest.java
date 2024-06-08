package com.slyko.cashitoinfra.adapter.api.dto;

import com.slyko.cashitoapplication.domain.Product;
import com.slyko.cashitoapplication.domain.ProductType;

import java.math.BigDecimal;

public record ProductRequest(String name, ProductType productType, BigDecimal cost) {

    public Product toDomain() {
        return new Product(name, productType, cost);
    }
}
