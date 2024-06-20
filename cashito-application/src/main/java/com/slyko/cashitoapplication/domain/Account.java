package com.slyko.cashitoapplication.domain;

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
    private String type;
}
