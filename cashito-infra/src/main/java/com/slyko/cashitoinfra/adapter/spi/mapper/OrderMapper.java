package com.slyko.cashitoinfra.adapter.spi.mapper;

import com.slyko.cashitoapplication.domain.Order;
import com.slyko.cashitoinfra.adapter.spi.entity.OrderEntity;

public class OrderMapper {

    public static Order toApi(OrderEntity db) {
        return new Order(db.getId(), db.getProducts(), db.getStatus(), db.getCost());
    }

    public static OrderEntity toDb(Order api) {
        return new OrderEntity(api.getId(), api.getStatus(), api.getProducts(), api.getCost());
    }
}
