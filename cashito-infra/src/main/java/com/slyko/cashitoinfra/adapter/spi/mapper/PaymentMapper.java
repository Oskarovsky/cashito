package com.slyko.cashitoinfra.adapter.spi.mapper;

import com.slyko.cashitoapplication.domain.Payment;
import com.slyko.cashitoinfra.adapter.spi.entity.PaymentEntity;

public class PaymentMapper {

    public static Payment toApi(PaymentEntity db) {
        return new Payment(
            db.getId(),
            db.getDealId(),
            db.getPaid(),
            db.getAccountId(),
            db.getCreatedDate()
        );
    }

    public static PaymentEntity toDb(Payment api) {
        return new PaymentEntity(
            api.id(),
            api.dealId(),
            api.paid(),
            api.accountId()
        );
    }
}
