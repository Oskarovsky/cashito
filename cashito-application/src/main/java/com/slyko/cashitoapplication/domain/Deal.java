package com.slyko.cashitoapplication.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Deal {

    private UUID id;
    private Long version;
    private String title;
    private Status status;
    private UUID accountId;
    private List<Product> products;

    public Deal(UUID id, Long version, String title, Status status, UUID accountId, List<Product> products) {
        this.id = id;
        this.version = version;
        this.title = title;
        this.accountId = accountId;
        this.status = status;
        this.products = products;
    }
}


