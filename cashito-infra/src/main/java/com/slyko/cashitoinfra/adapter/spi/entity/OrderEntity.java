package com.slyko.cashitoinfra.adapter.spi.entity;

import com.slyko.cashitoapplication.domain.Order;
import com.slyko.cashitoapplication.domain.Product;
import com.slyko.cashitoapplication.domain.Status;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
@NoArgsConstructor
public class OrderEntity {

    @Id
    UUID id;

    @Enumerated
    private Status status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<Product> products;

    private BigDecimal cost;

    public Order toApi() {
        return new Order(
            id,
            products,
            status,
            cost
        );
    }
}
