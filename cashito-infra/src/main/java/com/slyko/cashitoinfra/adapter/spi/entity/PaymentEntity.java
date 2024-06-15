package com.slyko.cashitoinfra.adapter.spi.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Table
@Getter
@Setter
public class PaymentEntity extends BaseEntity {

    UUID dealId;

    LocalDate paid;

    public PaymentEntity(UUID id, UUID dealId, LocalDate paid) {
        super(id, LocalDateTime.now(), LocalDateTime.now());
        this.dealId = dealId;
        this.paid = paid;
    }
}
