package com.slyko.cashitoapplication.request;


import com.slyko.cashitodomain.model.Deal;
import com.slyko.cashitodomain.model.DealStatus;

import java.util.UUID;

public record DealStatusRequest(
    UUID id,
    Long version,
    DealStatus status
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
