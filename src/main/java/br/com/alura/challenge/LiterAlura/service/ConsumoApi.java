package br.com.alura.challenge.LiterAlura.service;

mport java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.net.http.HttpClient;

import java.net.http.HttpResponse;

import java.net.http.HttpResponse;

import java.net.http.HttpRequest;

import java.net.http.HttpClient;

public class ConsumoApi {

    public String obterDados(String endereco) {
        HttpClient client = HttpClient.newHttpClient();
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException("Erro de conexão I/O ao acessar a API: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException("A conexão foi interrompida: " + e.getMessage());
        }

        return response.body();
    }
}