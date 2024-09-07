package com.slyko.cashitoapplication.request;


import com.slyko.cashitodomain.model.Deal;
import com.slyko.cashitodomain.model.DealStatus;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public record DealRequest(
    UUID id,
    Long version,
    String name,
    DealStatus status,
    UUID accountId,
    List<ProductRequest> products
) {

    public Deal toDomainCreate() {
        return new Deal(
            null,
            null,
            name,
            DealStatus.NEW,
            accountId,
            products != null
                    ? products.stream().map(ProductRequest::toDomain).toList()
                    : Collections.emptyList()
        );
    }

    public Deal toDomainUpdate() {
        return new Deal(
            id,
            version,
            name,
            status,
            accountId,
            null
        );
    }
}
