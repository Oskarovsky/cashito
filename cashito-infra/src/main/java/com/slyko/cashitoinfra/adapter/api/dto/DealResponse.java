package com.slyko.cashitoinfra.adapter.api.dto;

import com.slyko.cashitoapplication.domain.Deal;
import com.slyko.cashitoapplication.domain.Product;

import java.util.List;

public record DealResponse(List<Product> products) {

    public static DealResponse fromDomain(Deal deal) {
        return new DealResponse(
            deal.getProducts()
        );
    }
}
