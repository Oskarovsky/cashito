package com.slyko.cashitoinfra.adapter.spi.mapper;

import com.slyko.cashitoapplication.domain.Order;
import com.slyko.cashitoinfra.adapter.spi.entity.OrderEntity;
import com.slyko.cashitoinfra.adapter.spi.entity.ProductEntity;

public class OrderMapper {

    public static Order toApi(OrderEntity db) {
        return new Order(db.getId(), db.getProducts().stream().map(ProductEntity::toApi).toList(), db.getStatus());
    }

    public static OrderEntity toDb(Order api) {
        return new OrderEntity(api.getId(), api.getStatus(), api.getProducts().stream().map(ProductEntity::toDb).toList());
    }
}
