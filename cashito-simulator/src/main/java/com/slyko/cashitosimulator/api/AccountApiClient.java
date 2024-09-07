package com.slyko.cashitosimulator.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slyko.cashitosimulator.model.Account;
import com.slyko.cashitosimulator.model.AccountUpdate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class AccountApiClient {

    private final HttpClient client;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public AccountApiClient(HttpClient client) {
        this.client = client;
    }

    public Account createAccount(Account account) throws IOException, InterruptedException {
        String jsonBody = objectMapper.writeValueAsString(account);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/account"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(response.body(), Account.class);
    }

    public List<Account> getAccounts() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/account"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(response.body(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Account.class));
    }

    public Account updateAccount(String id, AccountUpdate accountUpdate, String etag) throws IOException, InterruptedException {
        String jsonBody = objectMapper.writeValueAsString(accountUpdate);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/account/" + id))
                .header("Content-Type", "application/json")
                .header("If-Match", etag)
                .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(response.body(), Account.class);
    }

    public static AccountApiClient createClient() {
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
        return new AccountApiClient(client);
    }
}