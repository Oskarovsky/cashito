package com.slyko.cashitoinfra.adapter.api.dto;

import com.slyko.cashitoapplication.domain.Product;
import com.slyko.cashitoapplication.domain.ProductType;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRequest(String name, ProductType productType, BigDecimal cost) {

    public Product toDomain() {
        return new Product(UUID.randomUUID(), name, productType, cost);
    }
}
