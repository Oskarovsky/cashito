package com.slyko.cashitoinfra.adapter.spi.entity;

import com.slyko.cashitoapplication.domain.Product;
import com.slyko.cashitoapplication.domain.ProductType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

//@Entity(name = "product")
@Table(name = "product")
@Getter
@Setter
public class ProductEntity extends BaseEntity {

    private String name;

    private ProductType productType;

    private BigDecimal cost;

//    @ManyToOne(fetch = FetchType.LAZY)
    private OrderEntity order;

    public ProductEntity(UUID id, String name, ProductType productType, BigDecimal cost) {
        super(id, LocalDateTime.now(), LocalDateTime.now());
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
