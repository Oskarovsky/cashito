package com.slyko.cashitoinfra.adapter.spi.entity;

import com.slyko.cashitoapplication.domain.Payment;
import com.slyko.cashitoapplication.domain.Product;
import com.slyko.cashitoapplication.domain.ProductType;
import com.slyko.cashitoapplication.domain.Provider;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Transient;
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

    private UUID providerId;

    @Transient
    private Provider provider;

    public ProductEntity(UUID id, String name, ProductType productType, BigDecimal cost, UUID dealId, UUID providerId) {
        super(id, LocalDateTime.now(), LocalDateTime.now());
        this.name = name;
        this.productType = productType;
        this.cost = cost;
        this.dealId = dealId;
        this.providerId = providerId;
    }
}
