package com.slyko.cashitoinfra.repository;


import com.slyko.cashitodomain.model.AccountType;
import com.slyko.cashitoinfra.adapter.secondary.entity.AccountEntity;
import com.slyko.cashitoinfra.adapter.secondary.repository.AccountReactiveRepository;
import org.junit.jupiter.api.BeforeEach;
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
public class AccountReactiveRepositoryTest {

    @Autowired
    AccountReactiveRepository accountReactiveRepository;

    @BeforeEach
    public void saveDB(){
    }

    @Test
    @DisplayName("Test create one business Account in database")
    public void shouldSaveOneAccountInDatabase(){
        AccountEntity account = new AccountEntity(null, "First Account", AccountType.BUSINESS);
        Mono<AccountEntity> accountEntityFlux = accountReactiveRepository.save(account);
        StepVerifier
                .create(accountEntityFlux)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    @DisplayName("Test create one public Account in database with specific name and type")
    public void shouldSaveAccountInDatabase(){
        // GIVEN
        var accountName = "Second Account";
        AccountEntity account = new AccountEntity(null, accountName, AccountType.PUBLIC);

        // WHEN
        Publisher<AccountEntity> setup = accountReactiveRepository.save(account);
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
