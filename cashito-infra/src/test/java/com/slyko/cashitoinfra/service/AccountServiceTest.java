package com.slyko.cashitoinfra.service;

import com.slyko.cashitoapplication.exception.AccountNotFoundException;
import com.slyko.cashitodomain.model.Account;
import com.slyko.cashitodomain.model.AccountType;
import com.slyko.cashitodomain.port.out.AccountsSecondaryPort;
import com.slyko.cashitodomain.service.AccountService;
import com.slyko.cashitoinfra.adapter.secondary.AccountDbAdapter;
import com.slyko.cashitoinfra.adapter.secondary.entity.AccountEntity;
import com.slyko.cashitoinfra.adapter.secondary.repository.AccountReactiveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AccountServiceTest {

    @MockBean
    private AccountReactiveRepository accountReactiveRepository;

    private AccountsSecondaryPort adapter;

    private AccountService accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        adapter = new AccountDbAdapter(accountReactiveRepository);
        accountService = new AccountService(adapter);
    }

    @Test
    void shouldGetAccountById() {
        // GIVEN
        var accountUuid = UUID.fromString("b7e12255-dc83-42b4-a6c2-09d1bf721445");
        AccountEntity account = new AccountEntity(accountUuid, 1L, "Init Account", AccountType.BUSINESS);
        when(accountReactiveRepository.findById(accountUuid)).thenReturn(Mono.just(account));

        // WHEN
        Mono<Account> accountMono = accountService.getById(accountUuid, 1L, false);

        // THEN
        StepVerifier
                .create(accountMono)
                .consumeNextWith(acc -> {
                    assertEquals(accountUuid, acc.getId());
                    assertEquals(1L, acc.getVersion());
                    assertEquals("Init Account", acc.getName());
                })
                .verifyComplete();
    }

    @Test
    void shouldGetExceptionWhenAccountNotFound() {
        // GIVEN
        var accountUuid = UUID.fromString("b7e12255-dc83-42b4-a6c2-09d1bf721445");
        when(accountReactiveRepository.findById(accountUuid)).thenReturn(Mono.empty());

        // WHEN
        Mono<Account> accountMono = accountService.getById(accountUuid, 1L, false);

        StepVerifier
                .create(accountMono)
                .expectErrorMatches(throwable -> throwable instanceof AccountNotFoundException)
                .verify();
    }

}