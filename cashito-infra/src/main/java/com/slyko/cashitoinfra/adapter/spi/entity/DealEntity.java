package com.slyko.cashitoinfra.adapter.spi.entity;

import com.slyko.cashitoapplication.domain.Deal;
import com.slyko.cashitoapplication.domain.Status;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Table(name = "deal")
@Getter
@Setter
@Accessors(chain = true)
public class DealEntity extends BaseEntity {

    @Enumerated
    private Status status;

    @Transient
    private List<ProductEntity> products;

    public DealEntity(UUID id, Status status, List<ProductEntity> products) {
        super(id, LocalDateTime.now(), LocalDateTime.now());
        this.status = status;
        this.products = products;
    }
}
