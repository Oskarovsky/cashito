package com.slyko.cashitoinfra.adapter.spi.mapper;

import com.slyko.cashitoapplication.domain.Deal;
import com.slyko.cashitoinfra.adapter.spi.entity.DealEntity;
import com.slyko.cashitoinfra.adapter.spi.entity.ProductEntity;

public class OrderMapper {

    public static Deal toApi(DealEntity db) {
        return new Deal(db.getId(), db.getProducts().stream().map(ProductEntity::toApi).toList(), db.getStatus());
    }

    public static DealEntity toDb(Deal api) {
        return new DealEntity(api.getId(), api.getStatus(), api.getProducts().stream().map(ProductEntity::toDb).toList());
    }
}
