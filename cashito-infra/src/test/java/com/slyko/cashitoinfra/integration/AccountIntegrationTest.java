package com.slyko.cashitoinfra.integration;

import com.slyko.cashitoapplication.exception.AccountNotFoundException;
import com.slyko.cashitodomain.model.Account;
import com.slyko.cashitodomain.model.AccountType;
import com.slyko.cashitodomain.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountIntegrationTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private AccountService accountService;

    @Test
    void shouldGetAccountById() {
        // WHEN
        var accountUuid = UUID.fromString("b7e12255-dc83-42b4-a6c2-09d1bf721445");
        var version = 1L;
        Account account = new Account(accountUuid, version, "Init Account", AccountType.BUSINESS);
        when(accountService.getById(accountUuid, null, false)).thenReturn(Mono.just(account));

        // THEN
        webClient
                .get().uri("/account/" + accountUuid)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Account.class);
    }

    @Test
    void shouldGetExceptionWhenGettingAccountById() {
        // WHEN
        var accountUuid = UUID.fromString("b7e12255-dc83-42b4-a6c2-09d1bf721445");
        when(accountService
                .getById(accountUuid, null, false))
                .thenThrow(new AccountNotFoundException(accountUuid));

        // THEN
        webClient
                .get().uri("/account/" + accountUuid)
                .exchange()
                .expectStatus()
                .isNotFound()
                .expectBody()
                .jsonPath("status")
                .isEqualTo(404);
    }
}
