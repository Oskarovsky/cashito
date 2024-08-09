package com.slyko.cashitoinfra.repository;


import com.slyko.cashitodomain.model.AccountType;
import com.slyko.cashitoinfra.TestData;
import com.slyko.cashitoinfra.adapter.secondary.entity.AccountEntity;
import com.slyko.cashitoinfra.adapter.secondary.repository.AccountReactiveRepository;
import com.slyko.cashitoinfra.adapter.secondary.repository.DealReactiveRepository;
import com.slyko.cashitoinfra.adapter.secondary.repository.ProductReactiveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataR2dbcTest
@ExtendWith(SpringExtension.class)
@Testcontainers
@Import(TestData.class)
class AccountReactiveRepositoryTest {

    @Autowired
    AccountReactiveRepository accountReactiveRepository;

    @Autowired
    DealReactiveRepository dealReactiveRepository;

    @Autowired
    ProductReactiveRepository productReactiveRepository;

    @Autowired
    private TestData testData;

    @BeforeEach
    public void setup() {
        testData.clearDatabase();
    }

    @Test
    @DisplayName("Test create one business Account in database")
    void shouldSaveOneAccountInDatabase() {
        // GIVEN
        AccountEntity account = new AccountEntity(null, null, "Init Account", AccountType.BUSINESS);

        // WHEN
        Mono<AccountEntity> accountEntityFlux = accountReactiveRepository.save(account);

        // THEN
        StepVerifier
                .create(accountEntityFlux)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    @DisplayName("Test create one public Account in database with private type")
    void shouldSavePrivateAccountInDatabase() {
        // GIVEN
        var accountName = "Priv Account";
        AccountEntity account = new AccountEntity(null, null, accountName, AccountType.PRIVATE);

        // WHEN
        StepVerifier.create(accountReactiveRepository.save(account))
                .expectNextMatches(acc -> acc.getId() != null)
                .verifyComplete();
    }

    @Test
    @DisplayName("Get empty list of accounts from database")
    void shouldGetEmptyListOfAccountsFromDatabase() {
        // WHEN
        accountReactiveRepository.findAll()
                .as(StepVerifier::create)
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    @DisplayName("Get list of accounts from database")
    void shouldGetListOfAccountsFromDatabase() {
        // WHEN
        testData.addAccountsToDatabase();

        // THEN
        accountReactiveRepository.findAll()
                .as(StepVerifier::create)
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    @DisplayName("Delete one Account by name from database")
    void shouldRemoveOneAccountFromByNameDatabase() {
        // GIVEN
        testData.addAccountsToDatabase();

        // WHEN
        Mono<Long> initialCount = accountReactiveRepository.count();
        Mono<long[]> finalCount = initialCount.flatMap(countBefore ->
                accountReactiveRepository
                        .findByName("First Account")
                        .flatMap(accountEntity ->
                                accountReactiveRepository.deleteById(accountEntity.getId())
                        )
                        .then(accountReactiveRepository.count())
                        .map(countAfter -> new long[]{countBefore, countAfter})
        );

        // THEN
        StepVerifier
                .create(initialCount)
                .expectNextMatches(count -> count > 0)
                .verifyComplete();

        // THEN
        StepVerifier
                .create(finalCount)
                .expectNextMatches(counts -> counts[1] == counts[0] - 1) // Ensure count is decreased by one
                .verifyComplete();
    }


    @Test
    @DisplayName("Test create one public Account in database with specific name and type")
    void shouldSaveAccountInDatabase() {
        // GIVEN
        var accountName = "Public Account";
        AccountEntity account = new AccountEntity(null, null, accountName, AccountType.PUBLIC);

        // WHEN
        Mono<AccountEntity> setup = accountReactiveRepository.save(account);
        Mono<AccountEntity> result = accountReactiveRepository.findByName(accountName);
        Publisher<AccountEntity> composite = Mono
                .from(setup)
                .thenMany(accountReactiveRepository.findAll())
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
