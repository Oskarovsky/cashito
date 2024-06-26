package com.slyko.cashitoapplication.request;


import com.slyko.cashitodomain.model.Account;
import com.slyko.cashitodomain.model.AccountType;

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
