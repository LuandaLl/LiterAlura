package br.com.alura.challenge.LiterAlura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.challenge.LiterAlura.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByIdioma(String idioma);
    Long countByIdioma(String idioma);
}