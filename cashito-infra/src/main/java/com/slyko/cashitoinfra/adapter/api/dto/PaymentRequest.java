package com.slyko.cashitoinfra.adapter.api.dto;


import com.slyko.cashitodomain.domain.Payment;

import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentRequest(
    UUID dealId,
    LocalDateTime paid,
    UUID accountId
) {

    public Payment toDomain() {
        return new Payment(
            null,
            dealId,
            paid,
            accountId
        );
    }
}
