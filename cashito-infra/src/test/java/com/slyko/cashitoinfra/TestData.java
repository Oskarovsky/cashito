package com.slyko.cashitoinfra;

import com.slyko.cashitodomain.model.Account;
import com.slyko.cashitodomain.model.AccountType;
import com.slyko.cashitoinfra.adapter.secondary.entity.AccountEntity;
import com.slyko.cashitoinfra.adapter.secondary.repository.AccountReactiveRepository;
import com.slyko.cashitoinfra.adapter.secondary.repository.DealReactiveRepository;
import com.slyko.cashitoinfra.adapter.secondary.repository.ProductReactiveRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Collections;
import java.util.List;

@DataR2dbcTest
@ExtendWith(SpringExtension.class)
public class TestData {

    private static final Logger log = LoggerFactory.getLogger(TestData.class);
    private static final String MARKER = "[TEST_DATABASE] >> {}}";

    @Autowired
    AccountReactiveRepository accountReactiveRepository;

    @Autowired
    DealReactiveRepository dealReactiveRepository;

    @Autowired
    ProductReactiveRepository productReactiveRepository;

    public void clearDatabase() {
        productReactiveRepository.deleteAll()
                .as(StepVerifier::create)
                .verifyComplete();
        dealReactiveRepository.deleteAll()
                .as(StepVerifier::create)
                .verifyComplete();
        accountReactiveRepository.deleteAll()
                .as(StepVerifier::create)
                .verifyComplete();
        log.info(MARKER, "Database has been cleaned.");
    }

    public void addAccountsToDatabase() {
        Flux<AccountEntity> accountEntityFlux = accountReactiveRepository.saveAll(
                List.of(
                        new AccountEntity(null, null, "Admin Account", AccountType.BUSINESS),
                        new AccountEntity(null, null, "First Account", AccountType.PUBLIC),
                        new AccountEntity(null, null, "Second Account", AccountType.PRIVATE),
                        new AccountEntity(null, null, "Third Account", AccountType.BUSINESS)
                )
        );
        StepVerifier.create(accountEntityFlux)
                .expectNextCount(4)
                .verifyComplete();
        log.info(MARKER, "Accounts has been added to database.");
    }
}
