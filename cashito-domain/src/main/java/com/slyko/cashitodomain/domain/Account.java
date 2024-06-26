package com.slyko.cashitodomain.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Account {
    private UUID id;
    private String name;
    private AccountType type;
}
