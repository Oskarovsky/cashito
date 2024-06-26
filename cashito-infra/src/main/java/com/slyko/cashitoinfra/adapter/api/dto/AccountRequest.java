package com.slyko.cashitoinfra.adapter.api.dto;


import com.slyko.cashitodomain.domain.Account;
import com.slyko.cashitodomain.domain.AccountType;

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
