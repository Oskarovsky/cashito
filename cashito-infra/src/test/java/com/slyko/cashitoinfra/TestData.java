package com.slyko.cashitoinfra;

import com.slyko.cashitodomain.model.Account;
import com.slyko.cashitodomain.model.AccountType;
import com.slyko.cashitoinfra.adapter.secondary.database.entity.AccountEntity;
import com.slyko.cashitoinfra.adapter.secondary.database.repository.AccountReactiveRepository;
import com.slyko.cashitoinfra.adapter.secondary.database.repository.DealReactiveRepository;
import com.slyko.cashitoinfra.adapter.secondary.database.repository.ProductReactiveRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

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
        Instant startTime = Instant.now(); // Start pomiaru czasu
        Flux<AccountEntity> accountEntityFlux = Flux
                .range(1, 1000)
                .map(index -> new AccountEntity(
                        null,
                        null,
                        "Admin Account " + index,
                        index % 2 == 0 ? AccountType.BUSINESS : AccountType.PRIVATE)
                )
                .subscribeOn(Schedulers.parallel())
                .flatMap(accountReactiveRepository::save)
                .doOnError(Throwable::printStackTrace)
                .doOnComplete(() -> {
                    Instant endTime = Instant.now();
                    Duration duration = Duration.between(startTime, endTime);
                    log.info(MARKER, String.format("Accounts has been added to database in %s milliseconds", duration.toMillis()));
                });

        StepVerifier.create(accountEntityFlux)
                .expectNextCount(1000)
                .verifyComplete();

        log.info(MARKER, "Accounts has been added to database.");
    }
}
