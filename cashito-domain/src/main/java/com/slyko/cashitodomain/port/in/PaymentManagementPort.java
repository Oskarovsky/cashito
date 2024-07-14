package com.slyko.cashitodomain.port.in;

import com.slyko.cashitodomain.model.Payment;

import java.util.UUID;

public abstract interface PaymentManagementPort extends BaseManagementPort<Payment, UUID> {

}
