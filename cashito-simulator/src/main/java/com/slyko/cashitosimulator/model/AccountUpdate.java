package com.slyko.cashitosimulator.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountUpdate {
    private String id;
    private Long version;
    private String name;
    private String type;

    public AccountUpdate(String id, Long version, String name, String type) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.type = type;
    }
}