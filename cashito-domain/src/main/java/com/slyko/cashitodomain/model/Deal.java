package com.slyko.cashitodomain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public non-sealed class Deal extends BaseApi {

    private UUID id;
    private Long version;
    private String title;
    private DealStatus status;
    private UUID accountId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Product> products;

    public Deal(UUID id, Long version, String title, DealStatus status, UUID accountId, List<Product> products) {
        this.id = id;
        this.version = version;
        this.title = title;
        this.accountId = accountId;
        this.status = status;
        this.products = products;
    }
}


