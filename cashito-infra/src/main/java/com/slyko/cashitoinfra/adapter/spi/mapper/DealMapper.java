package com.slyko.cashitoinfra.adapter.spi.mapper;

import com.slyko.cashitoapplication.domain.Deal;
import com.slyko.cashitoapplication.domain.Product;
import com.slyko.cashitoinfra.adapter.spi.entity.DealEntity;
import com.slyko.cashitoinfra.adapter.spi.entity.ProductEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DealMapper {

    public static Deal toApi(DealEntity db) {
        return new Deal(
            db.getId(),
            db.getTitle(),
            db.getStatus(),
            db.getAccountId(),
            convertProductsToApi(db.getProducts())
        );
    }

    private static List<Product> convertProductsToApi(List<ProductEntity> dbs) {
        return Optional.ofNullable(dbs)
                .map(list -> list.stream()
                        .map(ProductMapper::toApi)
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    public static DealEntity toDb(Deal api) {
        return new DealEntity(
            api.getId(),
            api.getTitle(),
            api.getStatus(),
            api.getAccountId(),
            convertProductsToDb(api.getProducts())
        );
    }

    private static List<ProductEntity> convertProductsToDb(List<Product> apis) {
        return Optional.ofNullable(apis)
                .map(list -> list.stream()
                        .map(ProductMapper::toDb)
                        .collect(Collectors.toList()))
                .orElse(null);
    }
}
