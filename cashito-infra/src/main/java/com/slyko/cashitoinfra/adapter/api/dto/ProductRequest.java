package com.slyko.cashitoinfra.adapter.api.dto;


import com.slyko.cashitodomain.domain.Product;
import com.slyko.cashitodomain.domain.ProductType;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRequest(
    String name,
    ProductType productType,
    BigDecimal cost,
    UUID dealId,
    UUID providerId
) {

    public Product toDomain() {
        return new Product(null, name, productType, cost, dealId, providerId);
    }
}
