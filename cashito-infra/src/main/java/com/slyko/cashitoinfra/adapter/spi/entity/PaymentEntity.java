package com.slyko.cashitoinfra.adapter.spi.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;

@Table
@Getter
@Setter
public class PaymentEntity extends BaseEntity {

    UUID orderId;

    LocalDate paid;

    public PaymentEntity(UUID id, UUID orderId, LocalDate paid) {
        super(id);
        this.orderId = orderId;
        this.paid = paid;
    }
}
