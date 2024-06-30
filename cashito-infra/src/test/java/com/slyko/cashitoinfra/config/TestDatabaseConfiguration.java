package com.slyko.cashitoinfra.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

@Configuration
public class TestDatabaseConfiguration {

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

}
