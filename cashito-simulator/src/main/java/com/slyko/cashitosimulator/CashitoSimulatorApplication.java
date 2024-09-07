package com.slyko.cashitosimulator;

import com.slyko.cashitosimulator.api.AccountApiClient;
import com.slyko.cashitosimulator.api.DealApiClient;
import com.slyko.cashitosimulator.handler.Simulation;
import com.slyko.cashitosimulator.model.Account;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class CashitoSimulatorApplication {

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        AccountApiClient accountApiClient = AccountApiClient.createClient();
        DealApiClient dealApiClient = DealApiClient.createClient();
        Simulation simulation = new Simulation(accountApiClient, dealApiClient);


        accountApiClient.createAccount(new Account(null, null, "ACC", "BUSINESS"));
        // Start the simulation loop
        while (true) {
            try {
                simulation.run();
            } catch (Exception e) {
                System.err.println("Failed to perform API operations: " + e.getMessage());
            }
            Thread.sleep(10000);  // Delay for 10 seconds before retrying
        }
    }

}
