package com.slyko.cashitoinfra.adapter.spi;

import com.slyko.cashitoapplication.domain.Order;
import com.slyko.cashitoapplication.port.out.OrdersSecondaryPort;
import com.slyko.cashitoinfra.adapter.spi.entity.OrderEntity;
import com.slyko.cashitoinfra.adapter.spi.mapper.OrderMapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class OrdersDbAdapter implements OrdersSecondaryPort {

    private final OrderReactiveRepository orderReactiveRepository;

    public OrdersDbAdapter(OrderReactiveRepository orderReactiveRepository) {
        this.orderReactiveRepository = orderReactiveRepository;
    }

    @Override
    public Mono<Order> findOrderById(UUID orderId) {
        return null;
    }

    @Override
    public Mono<Order> createOrder(Order order) {
        return orderReactiveRepository
                .save(OrderEntity.toDb(order))
                .map(OrderEntity::toApi);
    }

    @Override
    public void deleteOrderById(UUID orderId) {

    }
}
