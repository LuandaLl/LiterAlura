package br.com.alura.challenge.LiterAlura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDados implements IConverteDados {
    
    // O ObjectMapper é o "motor" do Jackson que faz a conversão
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            // Tenta ler o JSON e transformar na classe que você pedir (ex: RespostaApi.class)
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter o JSON para a classe Java: " + e.getMessage());
        }
    }
}