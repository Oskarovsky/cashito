package com.slyko.cashitoinfra.adapter.api.dto;


import com.slyko.cashitodomain.domain.Deal;

import java.util.List;
import java.util.UUID;

public record DealProductsRequest(
    UUID id,
    Long version,
    List<ProductRequest> products
) {

    public Deal toDomain() {
        return new Deal(
            id,
            version,
            null,
            null,
            null,
            products.stream().map(ProductRequest::toDomain).toList()
        );
    }
}
