package com.slyko.cashitoinfra;


import com.slyko.cashitodomain.model.AccountType;
import com.slyko.cashitoinfra.adapter.secondary.entity.AccountEntity;
import com.slyko.cashitoinfra.adapter.secondary.repository.AccountReactiveRepository;
import com.slyko.cashitoinfra.adapter.secondary.repository.ProductReactiveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
    @DisplayName("Test create one Account in database with specific name and type")
    public void shouldSaveOneAccountInDatabase(){
        AccountEntity account = new AccountEntity(null, "First Account", AccountType.BUSINESS);
        Mono<AccountEntity> accountEntityFlux = accountReactiveRepository.save(account);
        StepVerifier
                .create(accountEntityFlux)
                .expectNextCount(1)
                .verifyComplete();
    }

}
