package com.slyko.cashitoinfra.repository;


import com.slyko.cashitodomain.model.AccountType;
import com.slyko.cashitoinfra.adapter.secondary.entity.AccountEntity;
import com.slyko.cashitoinfra.adapter.secondary.repository.AccountReactiveRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataR2dbcTest
@ExtendWith(SpringExtension.class)
class AccountReactiveRepositoryTest {

    @Autowired
    AccountReactiveRepository accountReactiveRepository;

    @Test
    @DisplayName("Test create one business Account in database")
    void shouldSaveOneAccountInDatabase() {
        AccountEntity account = new AccountEntity(null, 1L, "First Account", AccountType.BUSINESS);
        Mono<AccountEntity> accountEntityFlux = accountReactiveRepository.save(account);
        StepVerifier
                .create(accountEntityFlux)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    @DisplayName("Get all accounts from database")
    void shouldGetAllAccountsFromDatabase() {
        // GIVEN

        // WHEN
        accountReactiveRepository.findAll()
                .as(StepVerifier::create)
                .expectNextCount(2)
                .verifyComplete();

        // THEN
    }

    @Test
    @DisplayName("Test create one public Account in database with private type")
    void shouldSavePrivateAccountInDatabase() {
        // GIVEN
        var accountName = "Priv Account";
        AccountEntity account = new AccountEntity(null, 1L, accountName, AccountType.PRIVATE);

        // WHEN
        StepVerifier.create(accountReactiveRepository.save(account))
                .expectNextMatches(acc -> acc.getId() != null)
                .verifyComplete();

    }


    @Test
    @DisplayName("Test create one public Account in database with specific name and type")
    void shouldSaveAccountInDatabase() {
        // GIVEN
        var accountName = "Public Account";
        AccountEntity account = new AccountEntity(null, 1L, accountName, AccountType.PUBLIC);

        // WHEN
        Mono<AccountEntity> setup = accountReactiveRepository.save(account);
        Mono<AccountEntity> result = accountReactiveRepository.findByName(accountName);
        Publisher<AccountEntity> composite = Mono
                .from(setup)
                .then(result);

        // THEN
        StepVerifier
                .create(composite)
                .consumeNextWith(db -> {
                    assertNotNull(db.getId());
                    assertNotNull(db.getName());
                    assertNotNull(db.getType());
                    assertEquals(accountName, account.getName());
                })
                .verifyComplete();
    }

}
