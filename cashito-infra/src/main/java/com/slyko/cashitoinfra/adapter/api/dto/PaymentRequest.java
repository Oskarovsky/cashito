package com.slyko.cashitoinfra.adapter.api.dto;

import com.slyko.cashitoapplication.domain.Payment;

import java.time.LocalDate;
import java.util.UUID;

public record PaymentRequest(UUID dealId, LocalDate paid, UUID accountId) {

    public Payment toDomain() {
        return new Payment(
            null,
            dealId,
            paid,
            accountId
        );
    }
}
