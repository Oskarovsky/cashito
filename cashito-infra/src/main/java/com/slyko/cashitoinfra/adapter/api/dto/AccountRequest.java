package com.slyko.cashitoinfra.adapter.api.dto;

import com.slyko.cashitoapplication.domain.Account;

import java.util.UUID;

public record AccountRequest(
    String name,
    String type
) {
    public Account toDomain() {
        return new Account(UUID.randomUUID(), name, type);
    }
}
