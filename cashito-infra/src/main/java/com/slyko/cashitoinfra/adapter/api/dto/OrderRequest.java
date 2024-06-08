package com.slyko.cashitoinfra.adapter.api.dto;

import com.slyko.cashitoapplication.domain.Order;
import com.slyko.cashitoapplication.domain.Product;

import java.util.List;

public record OrderRequest(List<ProductRequest> products) {

    public Order toDomain() {
        return new Order(products.stream().map(ProductRequest::toDomain).toList());
    }
}
