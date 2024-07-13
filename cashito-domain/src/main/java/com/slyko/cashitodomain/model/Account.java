package com.slyko.cashitodomain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public non-sealed class Account extends BaseApi {
    private UUID id;
    private Long version;
    private String name;
    private AccountType type;
}
