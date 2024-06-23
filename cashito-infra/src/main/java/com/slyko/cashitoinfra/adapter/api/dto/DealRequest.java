package com.slyko.cashitoinfra.adapter.api.dto;

import com.slyko.cashitoapplication.domain.Deal;
import com.slyko.cashitoapplication.domain.Status;

import java.util.List;
import java.util.UUID;

public record DealRequest(
    UUID id,
    Long version,
    String name,
    Status status,
    UUID accountId,
    List<ProductRequest> products
) {

    public Deal toDomainCreate() {
        return new Deal(
            null,
            null,
            name,
            Status.NEW,
            accountId,
            products.stream().map(ProductRequest::toDomain).toList()
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
