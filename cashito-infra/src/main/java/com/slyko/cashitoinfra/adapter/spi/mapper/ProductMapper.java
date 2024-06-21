package com.slyko.cashitoinfra.adapter.spi.mapper;

import com.slyko.cashitoapplication.domain.Product;
import com.slyko.cashitoinfra.adapter.spi.entity.ProductEntity;

public class ProductMapper {

    public static Product toApi(ProductEntity db) {
        return new Product(
            db.getId(),
            db.getName(),
            db.getProductType(),
            db.getCost(),
            db.getDealId(),
            db.getProviderId()
        );
    }

    public static ProductEntity toDb(Product api) {
        return new ProductEntity(
            api.id(),
            api.name(),
            api.type(),
            api.cost(),
            api.dealId(),
            api.providerId()
        );
    }

}
