package com.slyko.cashitoinfra.adapter.spi.entity;

import com.slyko.cashitoapplication.domain.Order;
import com.slyko.cashitoapplication.domain.Product;
import com.slyko.cashitoapplication.domain.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Table(name = "order")
@Getter
@Setter
@Accessors(chain = true)
public class OrderEntity extends BaseEntity {

    @Enumerated
    private Status status;

    @OneToMany(
        mappedBy = "order",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<ProductEntity> products;

    public OrderEntity(UUID id, Status status, List<ProductEntity> products) {
        super(id, LocalDateTime.now(), LocalDateTime.now());
        this.status = status;
        this.products = products;
    }

    public static OrderEntity toDb(Order order) {
        return new OrderEntity(
            order.getId(),
            order.getStatus(),
            order.getProducts().stream().map(ProductEntity::toDb).toList()
        );
    }

    public Order toApi() {
        return new Order(getId(), products.stream().map(ProductEntity::toApi).toList(), status);
    }
}
