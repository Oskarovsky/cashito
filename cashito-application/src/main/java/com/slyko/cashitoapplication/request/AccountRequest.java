package com.slyko.cashitoapplication.request;


import com.slyko.cashitodomain.model.Account;
import com.slyko.cashitodomain.model.AccountType;

import java.util.UUID;

public record AccountRequest(
    UUID id,
    Long version,
    String name,
    AccountType type
) {
    public Account toDomain() {
        return new Account(
            null,
            version,
            name,
            type
        );
    }

    public Account toDomainUpdate() {
        return new Account(
            id,
            version,
            name,
            type
        );
    }
}
