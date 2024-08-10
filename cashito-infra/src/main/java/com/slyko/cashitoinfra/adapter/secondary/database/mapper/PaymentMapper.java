package com.slyko.cashitoinfra.adapter.secondary.database.mapper;

import com.slyko.cashitodomain.model.Payment;
import com.slyko.cashitoinfra.adapter.secondary.database.entity.PaymentEntity;

public class PaymentMapper {

    public static Payment toApi(PaymentEntity db) {
        return new Payment(
            db.getId(),
            db.getDealId(),
            db.getPaid(),
            db.getAccountId()
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
