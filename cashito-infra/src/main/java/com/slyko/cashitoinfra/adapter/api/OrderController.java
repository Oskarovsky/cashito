package com.slyko.cashitoinfra.adapter.api;

import com.slyko.cashitoapplication.domain.Order;
import com.slyko.cashitoapplication.port.in.OrderingPaymentPort;
import com.slyko.cashitoinfra.adapter.api.dto.OrderRequest;
import com.slyko.cashitoinfra.adapter.api.dto.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderingPaymentPort orderingPaymentPort;

    @PostMapping
    public Mono<OrderResponse> createOrder(@RequestBody OrderRequest request) {
        Mono<Order> order = orderingPaymentPort.placeOrder(request.toDomain());
//        ResponseEntity<Mono<Order>> monoResponseEntity = new ResponseEntity<>(order, HttpStatus.OK);
//        System.out.println(order);
        return null;
    }

}
