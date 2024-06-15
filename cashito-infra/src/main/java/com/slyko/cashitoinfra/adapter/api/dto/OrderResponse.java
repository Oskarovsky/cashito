package com.slyko.cashitoinfra.adapter.api.dto;

import com.slyko.cashitoapplication.domain.Order;
import com.slyko.cashitoapplication.domain.Product;

import java.util.List;

public record OrderResponse(List<Product> products) {

    public static OrderResponse fromDomain(Order order) {
        return new OrderResponse(
            order.getProducts()
        );
    }
}
