package com.example.rest;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.UUID;

public class Client {
    private UUID personId;
    private String name;

    public Client(String name) {
        this.personId = UUID.randomUUID();
        this.name = name;
    }

    public static void main(String[] args) {
        Client cl = new Client("jos");
       //float balance = get("/balance");
    }
}
