package com.slyko.cashitoinfra;

import com.slyko.cashitoinfra.adapter.secondary.repository.AccountReactiveRepository;
import com.slyko.cashitoinfra.adapter.secondary.repository.DealReactiveRepository;
import com.slyko.cashitoinfra.adapter.secondary.repository.ProductReactiveRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

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

    public void prepareDatabase() {
        clearDatabase();
    }

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
}
