package com.slyko.cashitoapplication.request;


import com.slyko.cashitodomain.model.Product;
import com.slyko.cashitodomain.model.ProductType;

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
