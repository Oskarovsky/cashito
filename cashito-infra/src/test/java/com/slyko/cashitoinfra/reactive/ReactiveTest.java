package com.slyko.cashitoinfra.reactive;

import com.slyko.cashitodomain.port.out.AccountsSecondaryPort;
import com.slyko.cashitodomain.service.AccountService;
import com.slyko.cashitoinfra.TestData;
import com.slyko.cashitoinfra.adapter.secondary.database.AccountDbAdapter;
import com.slyko.cashitoinfra.adapter.secondary.database.entity.AccountEntity;
import com.slyko.cashitoinfra.adapter.secondary.database.repository.AccountReactiveRepository;
import com.slyko.cashitoinfra.adapter.secondary.database.repository.DealReactiveRepository;
import com.slyko.cashitoinfra.adapter.secondary.database.repository.ProductReactiveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.util.concurrent.atomic.AtomicLong;

@DataR2dbcTest
@ExtendWith(SpringExtension.class)
@Testcontainers
@Import(TestData.class)
class ReactiveTest {

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
        testData.addAccountsToDatabase();
    }

    @Test
    void shouldTestReactive() {
        Mono<AccountEntity> accountEntityMono = accountReactiveRepository.findByName("Admin Account");

        accountReactiveRepository
                .findByName("Admin Account")
                .subscribe(x -> System.out.println("Fetched account: " + x.getName()));

        StepVerifier.create(accountEntityMono)
                .expectNextMatches(account -> {
                    System.out.println("Fetched account: " + account.getName());
                    return true;
                })
                .expectComplete()
                .verify();
    }
}
