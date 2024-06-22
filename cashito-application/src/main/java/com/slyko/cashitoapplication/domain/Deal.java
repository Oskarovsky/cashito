package com.slyko.cashitoapplication.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Deal {

    private UUID id;
    private String title;
    private Status status;
    private UUID accountId;
    private List<Product> products;

    public Deal(UUID id, String title, Status status, UUID accountId, List<Product> products) {
        this.id = id;
        this.title = title;
        this.accountId = accountId;
        this.status = status;
        this.products = products;
    }
}


