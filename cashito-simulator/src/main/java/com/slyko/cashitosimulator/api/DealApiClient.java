package com.slyko.cashitosimulator.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slyko.cashitosimulator.model.Deal;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DealApiClient {

    private final HttpClient client;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public DealApiClient(HttpClient client) {
        this.client = client;
    }

    public Deal createDeal(Deal deal) throws IOException, InterruptedException {
        String jsonBody = objectMapper.writeValueAsString(deal);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/deal"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(response.body(), Deal.class);
    }

    public static DealApiClient createClient() {
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
        return new DealApiClient(client);
    }
}