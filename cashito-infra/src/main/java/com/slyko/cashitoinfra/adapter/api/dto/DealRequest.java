package com.slyko.cashitoinfra.adapter.api.dto;

import com.slyko.cashitoapplication.domain.Deal;
import com.slyko.cashitoapplication.domain.Status;

import java.util.List;
import java.util.UUID;

public record DealRequest(List<ProductRequest> products) {

    public Deal toDomain() {
        return new Deal(
            UUID.randomUUID(),
            products.stream().map(ProductRequest::toDomain).toList(),
            Status.TAKEN
        );
    }
}
