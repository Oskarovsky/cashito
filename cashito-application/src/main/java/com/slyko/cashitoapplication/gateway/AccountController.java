package com.slyko.cashitoapplication.gateway;

import com.slyko.cashitoapplication.request.AccountRequest;
import com.slyko.cashitodomain.port.in.AccountManagementPort;
import com.slyko.cashitodomain.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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
