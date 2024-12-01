package com.slyko.cashitosimulator;

import com.slyko.cashitosimulator.kafka.KafkaSender;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CashitoSimulatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CashitoSimulatorApplication.class, args);
    }

    @Bean
    CommandLineRunner sendMessage(KafkaSender kafkaSender) {
        return args -> {
            kafkaSender.sendMessage("cashito-topic", "Test message from Cashito Simulator");
        };
    }

}
