package com.slyko.cashitoinfra.adapter.api.dto;

import com.slyko.cashitoapplication.domain.Account;
import com.slyko.cashitoapplication.domain.AccountType;

public record AccountRequest(
    String name,
    AccountType type
) {
    public Account toDomain() {
        return new Account(
            null,
            name,
            type
        );
    }
}
