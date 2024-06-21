package com.slyko.cashitoapplication.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Deal {

    private UUID id = UUID.randomUUID();
    private final List<Product> products;
    private Status status = Status.PAYMENT_EXPECTED;

    public Deal(List<Product> products) {
        this.products = products;
    }

    public Deal(UUID id, List<Product> products, Status status) {
        this.id = id;
        this.products = products;
        this.status = status;
    }
}


