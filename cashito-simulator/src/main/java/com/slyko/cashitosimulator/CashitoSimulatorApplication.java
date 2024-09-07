package com.slyko.cashitosimulator;

import com.slyko.cashitosimulator.api.AccountApiClient;
import com.slyko.cashitosimulator.api.DealApiClient;
import com.slyko.cashitosimulator.handler.Simulation;
import com.slyko.cashitosimulator.model.Account;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class CashitoSimulatorApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        AccountApiClient accountApiClient = AccountApiClient.createClient();
        DealApiClient dealApiClient = DealApiClient.createClient();
        Simulation simulation = new Simulation(accountApiClient, dealApiClient);


        accountApiClient.createAccount(new Account(null, null, "ACC", "BUSINESS"));

        int threadCount = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        Random random = new Random();
        Callable<Void> task = () -> {
            while (true) {
                try {
                    simulation.run();
                } catch (Exception e) {
                    System.err.println("Failed to perform API operations: " + e.getMessage());
                }
                long delay = 1 + random.nextInt(41); // random value between 10 and 100
                Thread.sleep(delay);
            }
        };

        executorService.invokeAll(List.of(task, task, task, task, task, task, task, task, task, task));
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    }

}
