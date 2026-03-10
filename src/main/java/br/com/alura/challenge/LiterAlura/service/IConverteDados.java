package br.com.alura.challenge.LiterAlura.service;


public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}