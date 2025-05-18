package com.example.APIFilmesOMDb.converter;

import com.example.APIFilmesOMDb.dto.FilmeDTO;
import com.example.APIFilmesOMDb.model.Filme;
import com.example.APIFilmesOMDb.vo.FilmeVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FilmeConverterTest {

    private FilmeConverter converter;

    @BeforeEach
    void setUp() {
        converter = new FilmeConverter();
    }

    @Test
    void deveConverterFilmeDTOParaFilme() {
        FilmeDTO dto = new FilmeDTO();
        dto.setTitle("Interestelar");
        dto.setYear("2014");

        Filme filme = converter.converteParaFilme(dto);

        assertNotNull(filme);
        assertEquals("Interestelar", filme.getTitle());
        assertEquals("2014", filme.getYear());
    }

    @Test
    void deveLancarExcecaoQuandoFilmeDTONulo() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                converter.converteParaFilme(null));

        assertEquals("FilmeDTO não pode ser nulo", exception.getMessage());
    }

    @Test
    void deveConverterFilmeParaFilmeVO() {
        Filme filme = new Filme();
        filme.setId(1L);
        filme.setTitle("Inception");
        filme.setYear("2010");

        FilmeVO filmeVO = converter.converteParaFilmeVO(filme);

        assertNotNull(filmeVO);
        assertEquals(1L, filmeVO.getId());
        assertEquals("Inception", filmeVO.getTitle());
        assertEquals("2010", filmeVO.getYear());
    }

    @Test
    void deveLancarExcecaoQuandoFilmeNulo() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                converter.converteParaFilmeVO(null));

        assertEquals("Filme não pode ser nulo", exception.getMessage());
    }
}

