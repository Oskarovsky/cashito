package com.slyko.cashitoapplication.request;


import com.slyko.cashitodomain.model.Deal;
import com.slyko.cashitodomain.model.Status;

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
