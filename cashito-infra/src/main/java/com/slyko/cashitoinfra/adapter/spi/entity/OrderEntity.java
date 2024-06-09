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
import java.util.List;
import java.util.UUID;

@Table
@Getter
@Setter
@Accessors(chain = true)
public class OrderEntity extends BaseEntity {

    @Enumerated
    private Status status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<Product> products;

    private BigDecimal cost;

    public OrderEntity(UUID id, Status status, List<Product> products, BigDecimal cost) {
        super(id);
        this.status = status;
        this.products = products;
        this.cost = cost;
    }

    public static OrderEntity toDb(Order order) {
        return new OrderEntity(
            order.getId(),
            order.getStatus(),
            order.getProducts(),
            order.getCost()
        );
    }

    public Order toApi() {
        return new Order(getId(), products, status, cost);
    }
}
