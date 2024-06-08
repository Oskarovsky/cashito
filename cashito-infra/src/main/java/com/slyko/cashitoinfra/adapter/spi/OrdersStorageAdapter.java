package com.slyko.cashitoinfra.adapter.spi;

import com.slyko.cashitoapplication.domain.Order;
import com.slyko.cashitoapplication.port.spi.OrdersSecondaryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrdersStorageAdapter implements OrdersSecondaryPort {

    @Override
    public Order findOrderById(UUID orderId) {
        return null;
    }

    @Override
    public Order save(Order order) {
        return null;
    }

    @Override
    public void deleteById(UUID orderId) {

    }
}
