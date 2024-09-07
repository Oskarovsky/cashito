package com.slyko.cashitosimulator.handler;


import com.slyko.cashitosimulator.api.AccountApiClient;
import com.slyko.cashitosimulator.api.DealApiClient;
import com.slyko.cashitosimulator.model.Account;
import com.slyko.cashitosimulator.model.AccountUpdate;
import com.slyko.cashitosimulator.model.Deal;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;

public class Simulation {

    private final AccountApiClient accountApiClient;
    private final DealApiClient dealApiClient;

    public Simulation(AccountApiClient accountApiClient, DealApiClient dealApiClient) {
        this.accountApiClient = accountApiClient;
        this.dealApiClient = dealApiClient;
    }

    public void run() throws IOException, InterruptedException, ExecutionException {
        simulateAccounts();
        simulateDeals();
    }

    private void simulateAccounts() throws IOException, InterruptedException {
        // Create a new account
        accountApiClient.createAccount(new Account(null, null, "Account " + new Random().nextInt(1000), "BUSINESS"));

        // Wait for a bit
        Thread.sleep(1000);

        // Get all accounts
        List<Account> accounts = accountApiClient.getAccounts();

        // Update an account
        if (false) {
            Account accountToUpdate = accounts.get(new Random().nextInt(accounts.size()));
            Account updatedAccount = accountApiClient.updateAccount(
                    accountToUpdate.getId(),
                    new AccountUpdate(accountToUpdate.getId(), accountToUpdate.getVersion(), "Updated " + accountToUpdate.getName(), "PRIVATE"),
                    accountToUpdate.getVersion().toString()
            );
            System.out.println("Updated account: " + updatedAccount);
        }
    }

    private void simulateDeals() throws IOException, InterruptedException {
        List<Account> accounts = accountApiClient.getAccounts();
        if (!accounts.isEmpty()) {
            Account randomAccount = accounts.get(ThreadLocalRandom.current().nextInt(accounts.size()));
            // Create new deal
            Deal newDeal = dealApiClient
                    .createDeal(
                            new Deal(null, null, "Deal " + new Random().nextInt(1000), "NEW", randomAccount.getId(), List.of()));
            System.out.printf("Created deal: %s for account %s%n", newDeal, randomAccount.getId());
        }
        // Wait for a bit
        Thread.sleep(1000);
    }
}