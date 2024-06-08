package com.slyko.cashitoinfra.adapter.spi.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class PaymentEntity {

    @Id
    UUID id;

    UUID orderId;

    LocalDate paid;
}
