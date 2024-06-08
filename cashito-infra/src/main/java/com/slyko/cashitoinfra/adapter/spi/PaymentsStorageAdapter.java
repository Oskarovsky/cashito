package com.slyko.cashitoinfra.adapter.spi;

import com.slyko.cashitoapplication.domain.Payment;
import com.slyko.cashitoapplication.port.spi.PaymentsSecondaryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PaymentsStorageAdapter implements PaymentsSecondaryPort {

    @Override
    public Payment findPaymentByOrderId(UUID orderId) {
        return null;
    }

    @Override
    public Payment save(Payment payment) {
        return null;
    }
}
