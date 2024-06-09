package com.slyko.cashitoinfra.adapter.spi.entity;

import com.slyko.cashitoapplication.domain.Product;
import com.slyko.cashitoapplication.domain.ProductType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "product")
@Getter
@Setter
public class ProductEntity extends BaseEntity {

    private String name;

    private ProductType productType;

    private BigDecimal cost;

    public ProductEntity(UUID id, String name, ProductType productType, BigDecimal cost) {
        super(id);
        this.name = name;
        this.productType = productType;
        this.cost = cost;
    }

    public static ProductEntity toDb(Product product) {
        return new ProductEntity(product.id(), product.name(), product.type(), product.cost());
    }

    public Product toApi() {
        return new Product(getId(), name, productType, cost);
    }
}
