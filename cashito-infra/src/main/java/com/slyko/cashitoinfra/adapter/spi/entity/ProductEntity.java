package com.slyko.cashitoinfra.adapter.spi.entity;

import com.slyko.cashitoapplication.domain.Product;
import com.slyko.cashitoapplication.domain.ProductType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class ProductEntity extends BaseEntity {

    private String name;

    private ProductType productType;

    private BigDecimal cost;

    private UUID dealId;

    public ProductEntity(UUID id, String name, ProductType productType, BigDecimal cost, UUID dealId) {
        super(id, LocalDateTime.now(), LocalDateTime.now());
        this.name = name;
        this.productType = productType;
        this.cost = cost;
        this.dealId = dealId;
    }

    public static ProductEntity toDb(Product product) {
        return new ProductEntity(product.id(), product.name(), product.type(), product.cost(), product.dealId());
    }

    public Product toApi() {
        return new Product(getId(), name, productType, cost, dealId);
    }
}
