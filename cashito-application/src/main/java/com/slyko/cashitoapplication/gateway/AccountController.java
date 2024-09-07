package com.slyko.cashitoapplication.gateway;

import com.slyko.cashitoapplication.request.AccountRequest;
import com.slyko.cashitodomain.model.Account;
import com.slyko.cashitodomain.port.in.AccountManagementPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.noContent;

@RestController
@RequestMapping(value = "/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountManagementPort accountManagementPort;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Account> getAccounts() {
        return accountManagementPort.getAll();
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Mono<Account> getAccount(
            @PathVariable(value = "id") UUID id,
            @RequestParam(value = "relations", defaultValue = "false") boolean loadRelations
    ) {
        return accountManagementPort.getById(id, null, loadRelations);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Account> createAccount(@RequestBody AccountRequest request) {
        return accountManagementPort.create(request.toDomain());
    }

    @PutMapping(value = "/{id}")
    public Mono<Account> update(
            @PathVariable final UUID id,
            @RequestHeader(value = HttpHeaders.IF_MATCH) final Long version,
            @RequestBody final AccountRequest request
    ) {
        return accountManagementPort.update(id, version, request.toDomainUpdate());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ResponseEntity<Void>> deleteAccount(
            @PathVariable UUID id,
            @RequestHeader(value = HttpHeaders.IF_MATCH) final Long version) {
        return accountManagementPort
                .deleteById(id, version)
                .then(Mono.fromCallable(() -> noContent().build()));
    }
}
