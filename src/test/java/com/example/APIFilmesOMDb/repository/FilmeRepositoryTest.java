package com.example.APIFilmesOMDb.repository;
import com.example.APIFilmesOMDb.model.Filme;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class FilmeRepositoryTest {

    @Autowired
    private FilmeRepository filmeRepository;

    @Test
    void deveSalvarERecuperarFilme() {
        Filme filme = new Filme();
        filme.setTitle("Matrix");
        filme.setYear("1999");

        Filme filmeSalvo = filmeRepository.save(filme);
        Optional<Filme> encontrado = filmeRepository.findById(filmeSalvo.getId());

        assertTrue(encontrado.isPresent());
        assertEquals("Matrix", encontrado.get().getTitle());
        assertEquals("1999", encontrado.get().getYear());
    }

    @Test
    void deveExcluirFilme() {
        Filme filme = new Filme();
        filme.setTitle("Avatar");
        filme.setYear("2009");

        Filme filmeSalvo = filmeRepository.save(filme);
        Long id = filmeSalvo.getId();

        filmeRepository.deleteById(id);
        Optional<Filme> encontrado = filmeRepository.findById(id);

        assertFalse(encontrado.isPresent());
    }

    @Test
    void deveRetornarVazioQuandoFilmeNaoExistir() {
        Optional<Filme> filme = filmeRepository.findById(999L);
        assertFalse(filme.isPresent());
    }
}
