package com.slyko.cashitoinfra.adapter.secondary.mapper;

import com.slyko.cashitodomain.model.Product;
import com.slyko.cashitoinfra.adapter.secondary.entity.ProductEntity;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public static Collection<UUID> extractTagIdsFromTags(Collection<Product> products) {
        if (products == null) {
            return new LinkedHashSet<>();
        }

        return products.stream()
                .map(Product::id)
                .collect(Collectors.toSet());
    }

}
