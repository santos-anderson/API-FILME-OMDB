package com.example.APIFilmesOMDb.service;
import com.example.APIFilmesOMDb.Client.FilmeClientOMDBFeign;
import com.example.APIFilmesOMDb.converter.FilmeConverter;
import com.example.APIFilmesOMDb.dto.FilmeDTO;
import com.example.APIFilmesOMDb.exception.ErroIntegracaoOMDBException;
import com.example.APIFilmesOMDb.exception.FilmeNaoEncontradoException;
import com.example.APIFilmesOMDb.exception.RequisicaoInvalidaException;
import com.example.APIFilmesOMDb.model.Filme;
import com.example.APIFilmesOMDb.repository.FilmeRepository;
import com.example.APIFilmesOMDb.vo.FilmeOMDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FilmeServiceTest {

    @InjectMocks
    private FilmeService filmeService;

    @Mock
    private FilmeClientOMDBFeign filmeClientFeign;

    @Mock
    private FilmeRepository filmeRepository;

    @Mock
    private FilmeConverter filmeConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(filmeService, "apiKey", "dummyKey");
    }


    @Test
    void getFilme_DeveRetornarFilmeOMDB_QuandoTemaValido() {
        FilmeOMDB filmeOMDB = new FilmeOMDB(); // Simulando o retorno esperado de FilmeOMDB
        when(filmeClientFeign.getFilme("Matrix", "dummyKey")).thenReturn(filmeOMDB); // Configurando o mock

        FilmeOMDB resultado = filmeService.getFilme("Matrix"); // Chama o método

        assertNotNull(resultado);  // Verifica se o retorno não é nulo
        verify(filmeClientFeign).getFilme("Matrix", "dummyKey");  // Verifica se o Feign Client foi chamado corretamente
    }


    @Test
    void getFilme_DeveLancarExcecao_QuandoTemaInvalido() {

        RequisicaoInvalidaException ex1 = assertThrows(RequisicaoInvalidaException.class,
                () -> filmeService.getFilme(null));
        assertEquals("O título do filme (tema) não pode ser nulo ou vazio.", ex1.getMessage());


        RequisicaoInvalidaException ex2 = assertThrows(RequisicaoInvalidaException.class,
                () -> filmeService.getFilme(" "));
        assertEquals("O título do filme (tema) não pode ser nulo ou vazio.", ex2.getMessage());
    }


    @Test
    void getFilme_DeveLancarErroIntegracaoOMDBException_QuandoChamadaFalhar() {
        // Simulando erro ao consultar a API OMDB
        when(filmeClientFeign.getFilme("Matrix", "dummyKey")).thenThrow(new RuntimeException("Falha na comunicação"));

        ErroIntegracaoOMDBException ex = assertThrows(ErroIntegracaoOMDBException.class,
                () -> filmeService.getFilme("Matrix"));

        assertTrue(ex.getMessage().contains("Erro ao consultar a API OMDB"));
    }


    @Test
    void save_DeveSalvarFilme_QuandoFilmeDTONaoNulo() {
        FilmeDTO dto = new FilmeDTO();
        Filme filme = new Filme();

        when(filmeConverter.converteParaFilme(dto)).thenReturn(filme);
        when(filmeRepository.save(filme)).thenReturn(filme);

        Filme resultado = filmeService.save(dto);

        assertNotNull(resultado);
        verify(filmeRepository).save(filme);
    }


    @Test
    void save_DeveLancarExcecao_QuandoFilmeDTONulo() {
        RequisicaoInvalidaException ex = assertThrows(RequisicaoInvalidaException.class,
                () -> filmeService.save(null));

        assertEquals("FilmeDTO não pode ser nulo.", ex.getMessage());
    }


    @Test
    void getById_DeveRetornarFilme_QuandoExistente() {
        Filme filme = new Filme();
        when(filmeRepository.findById(1L)).thenReturn(Optional.of(filme));

        Filme resultado = filmeService.getById(1L);

        assertNotNull(resultado);
    }


    @Test
    void getById_DeveLancarExcecao_QuandoInexistente() {
        when(filmeRepository.findById(1L)).thenReturn(Optional.empty());

        FilmeNaoEncontradoException ex = assertThrows(FilmeNaoEncontradoException.class,
                () -> filmeService.getById(1L));

        assertEquals("Filme não encontrado com o ID: 1", ex.getMessage());
    }


    @Test
    void listarTodos_DeveRetornarListaDeFilmes() {
        List<Filme> filmes = Arrays.asList(new Filme(), new Filme());
        when(filmeRepository.findAll()).thenReturn(filmes);

        List<Filme> resultado = filmeService.listarTodos();

        assertEquals(2, resultado.size());
    }


    @Test
    void deletarPorId_DeveDeletarFilme_QuandoExistente() {
        when(filmeRepository.existsById(1L)).thenReturn(true);

        filmeService.deletarPorId(1L);

        verify(filmeRepository).deleteById(1L);
    }


    @Test
    void deletarPorId_DeveLancarExcecao_QuandoFilmeNaoExistente() {
        when(filmeRepository.existsById(1L)).thenReturn(false);

        FilmeNaoEncontradoException ex = assertThrows(FilmeNaoEncontradoException.class,
                () -> filmeService.deletarPorId(1L));

        assertEquals("Não foi possível deletar. Filme com ID 1 não encontrado.", ex.getMessage());
    }
}



