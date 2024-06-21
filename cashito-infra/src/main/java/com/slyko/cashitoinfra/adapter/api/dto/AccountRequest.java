package com.slyko.cashitoinfra.adapter.api.dto;

import com.slyko.cashitoapplication.domain.Account;

public record AccountRequest(
    String name,
    String type
) {
    public Account toDomain() {
        return new Account(
            null,
            name,
            type
        );
    }
}
