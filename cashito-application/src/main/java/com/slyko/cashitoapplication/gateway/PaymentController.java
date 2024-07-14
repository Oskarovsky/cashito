package com.slyko.cashitoapplication.gateway;

import com.slyko.cashitoapplication.request.PaymentRequest;
import com.slyko.cashitodomain.port.in.PaymentManagementPort;
import com.slyko.cashitodomain.model.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping(value = "/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentManagementPort paymentManagementPort;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Payment> getPayments() {
        return paymentManagementPort.getAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Payment> getPayments(@PathVariable UUID id) {
        return paymentManagementPort.getById(id, null, false);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Payment> createPayment(@RequestBody PaymentRequest request) {
        return paymentManagementPort.create(request.toDomain());
    }
}
