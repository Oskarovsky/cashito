package com.slyko.cashitoinfra;


import com.slyko.cashitodomain.model.AccountType;
import com.slyko.cashitoinfra.adapter.secondary.entity.AccountEntity;
import com.slyko.cashitoinfra.adapter.secondary.repository.AccountReactiveRepository;
import com.slyko.cashitoinfra.adapter.secondary.repository.ProductReactiveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataR2dbcTest
@ExtendWith(SpringExtension.class)
public class RepositoryTest {

    @Autowired
    ProductReactiveRepository productReactiveRepository;

    @Autowired
    AccountReactiveRepository accountReactiveRepository;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>()
            .withUsername("admin")
            .withPassword("password")
            .withDatabaseName("test_cashito");

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        postgres.start();
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.liquibase.contexts", () -> "!prod");
    }

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
