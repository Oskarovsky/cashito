package com.slyko.cashitosimulator.handler;


import com.slyko.cashitosimulator.api.AccountApiClient;
import com.slyko.cashitosimulator.api.DealApiClient;
import com.slyko.cashitosimulator.model.Account;
import com.slyko.cashitosimulator.model.AccountUpdate;
import com.slyko.cashitosimulator.model.Deal;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Simulation {

    private final AccountApiClient accountApiClient;
    private final DealApiClient dealApiClient;

    public Simulation(AccountApiClient accountApiClient, DealApiClient dealApiClient) {
        this.accountApiClient = accountApiClient;
        this.dealApiClient = dealApiClient;
    }

    public void run() throws IOException, InterruptedException, ExecutionException {
        simulateDeals();
    }

    private void simulateAccounts() throws IOException, InterruptedException, ExecutionException {
        // Create a new account
        Account newAccount = accountApiClient.createAccount(new Account(null, null, "Account " + new Random().nextInt(1000), "BUSINESS"));
        System.out.println("Created account: " + newAccount);

        // Wait for a bit
        Thread.sleep(1000);

        // Get all accounts
        List<Account> accounts = accountApiClient.getAccounts();
        System.out.println("Current accounts: " + accounts);

        // Update an account
        if (!accounts.isEmpty()) {
            Account accountToUpdate = accounts.get(new Random().nextInt(accounts.size()));
            Account updatedAccount = accountApiClient.updateAccount(
                    accountToUpdate.getId(),
                    new AccountUpdate(accountToUpdate.getId(), accountToUpdate.getVersion(), "Updated " + accountToUpdate.getName(), "PRIVATE"),
                    accountToUpdate.getVersion().toString()
            );
            System.out.println("Updated account: " + updatedAccount);
        }
    }

    private void simulateDeals() throws IOException, InterruptedException, ExecutionException {
        String accId = accountApiClient.getAccounts().get(2).getId();
        System.out.println("DEEJYA -- > " + accId);

        // Create new deal
        Deal newDeal = dealApiClient.createDeal(new Deal(null, null, "Deal " + new Random().nextInt(1000), "NEW", accId, List.of()));
        System.out.println("Created deal: " + newDeal);

        // Wait for a bit
        Thread.sleep(1000);
    }
}