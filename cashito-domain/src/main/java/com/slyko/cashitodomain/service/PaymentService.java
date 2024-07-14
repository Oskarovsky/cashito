package com.slyko.cashitodomain.service;

import com.slyko.cashitodomain.model.Payment;
import com.slyko.cashitodomain.port.in.PaymentManagementPort;
import com.slyko.cashitodomain.port.out.PaymentsSecondaryPort;
import com.slyko.cashitodomain.util.UseCase;

import java.util.UUID;

@UseCase
public class PaymentService extends BaseService<Payment, UUID> implements PaymentManagementPort {

    private final PaymentsSecondaryPort paymentsSecondaryPort;

    public PaymentService(PaymentsSecondaryPort paymentsSecondaryPort) {
        super(paymentsSecondaryPort);
        this.paymentsSecondaryPort = paymentsSecondaryPort;
    }
}
