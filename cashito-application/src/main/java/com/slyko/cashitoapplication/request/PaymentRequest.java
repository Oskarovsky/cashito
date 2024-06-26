package com.slyko.cashitoapplication.request;


import com.slyko.cashitodomain.model.Payment;

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
