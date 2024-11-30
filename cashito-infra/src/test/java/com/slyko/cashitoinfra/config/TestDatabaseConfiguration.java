package com.slyko.cashitoinfra.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;

@Configuration
public class TestDatabaseConfiguration {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>()
            .withUsername("admin")
            .withPassword("password")
            .withDatabaseName("test_cashito");
    static {
        postgres.setPortBindings(List.of("5455:5432"));
        postgres.start();
    }

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.liquibase.contexts", () -> "!prod");
    }

    private static String r2dbcUrl() {
        return String.format("r2dbc:postgresql://%s:%s/%s",
                postgres.getContainerIpAddress(),
                postgres.getMappedPort(5455),
                postgres.getDatabaseName());
    }

}
