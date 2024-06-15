package com.slyko.cashitoinfra.adapter.api;

import com.slyko.cashitoapplication.domain.Account;
import com.slyko.cashitoapplication.domain.Order;
import com.slyko.cashitoapplication.port.in.AccountManagementPort;
import com.slyko.cashitoinfra.adapter.api.dto.AccountRequest;
import com.slyko.cashitoinfra.adapter.api.dto.OrderRequest;
import com.slyko.cashitoinfra.adapter.api.dto.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountManagementPort accountManagementPort;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Account> getAccounts() {
        return accountManagementPort.getAccounts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Account> createAccount(@RequestBody AccountRequest request) {
        return accountManagementPort.createAccount(request.toDomain());
    }
}
