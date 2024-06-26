package com.slyko.cashitoinfra.adapter.api.dto;

import com.slyko.cashitoapplication.domain.Deal;
import com.slyko.cashitoapplication.domain.Status;

import java.util.UUID;

public record DealStatusRequest(
    UUID id,
    Long version,
    Status status
) {
    public Deal toDomain() {
        return new Deal(
                id,
                version,
                null,
                status,
                null,
                null
        );
    }
}
