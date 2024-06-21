package com.slyko.cashitoinfra.adapter.spi.mapper;

import com.slyko.cashitoapplication.domain.Deal;
import com.slyko.cashitoinfra.adapter.spi.entity.DealEntity;

public class DealMapper {

    public static Deal toApi(DealEntity db) {
        return new Deal(
            db.getId(),
            db.getTitle(),
            db.getStatus(),
            db.getAccountId(),
            db.getProducts().stream().map(ProductMapper::toApi).toList()
        );
    }

    public static DealEntity toDb(Deal api) {
        return new DealEntity(
            api.getId(),
            api.getTitle(),
            api.getStatus(),
            api.getAccountId(),
            api.getProducts().stream().map(ProductMapper::toDb).toList()
        );
    }
}
