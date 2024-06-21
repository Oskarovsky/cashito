package com.slyko.cashitoinfra.adapter.api;

import com.slyko.cashitoapplication.domain.Payment;
import com.slyko.cashitoapplication.port.in.PaymentManagementPort;
import com.slyko.cashitoinfra.adapter.api.dto.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentManagementPort paymentManagementPort;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Payment> getPayments() {
        return paymentManagementPort.getPayments();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Payment> createPayment(@RequestBody PaymentRequest request) {
        return paymentManagementPort.createPayment(request.toDomain());
    }
}
