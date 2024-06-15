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


    public Deal markPaid() {
        if (status != Status.PAYMENT_EXPECTED) {
            throw new IllegalStateException("Deal is already paid");
        }
        status = Status.PAID;
        return this;
    }

    public Deal markBeingPrepared() {
        if (status != Status.PAID) {
            throw new IllegalStateException("Deal is not paid");
        }
        status = Status.PREPARING;
        return this;
    }

    public Deal markPrepared() {
        if (status != Status.PREPARING) {
            throw new IllegalStateException("Deal is not being prepared");
        }
        status = Status.READY;
        return this;
    }

    public Deal markTaken() {
        if (status != Status.READY) {
            throw new IllegalStateException("Deal is not ready");
        }
        status = Status.TAKEN;
        return this;
    }

}


