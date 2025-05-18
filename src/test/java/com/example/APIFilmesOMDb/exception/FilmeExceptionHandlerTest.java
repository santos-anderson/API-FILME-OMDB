package com.example.APIFilmesOMDb.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class FilmeExceptionHandlerTest {

    private FilmeExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new FilmeExceptionHandler();
    }

    @Test
    void testHandleFilmeNaoEncontrado() {
        FilmeNaoEncontradoException ex = new FilmeNaoEncontradoException("Filme não encontrado.");
        ResponseEntity<?> response = handler.handleFilmeNaoEncontrado(ex);

        assertEquals(404, response.getStatusCodeValue());

        Map<?, ?> body = (Map<?, ?>) response.getBody();
        assertNotNull(body);
        assertEquals("Filme não encontrado.", body.get("mensagem"));
        assertEquals(404, body.get("status"));
        assertNotNull(body.get("timestamp"));
    }

    @Test
    void testHandleRequisicaoInvalida() {
        RequisicaoInvalidaException ex = new RequisicaoInvalidaException("Requisição inválida.");
        ResponseEntity<?> response = handler.handleRequisicaoInvalida(ex);

        assertEquals(400, response.getStatusCodeValue());

        Map<?, ?> body = (Map<?, ?>) response.getBody();
        assertNotNull(body);
        assertEquals("Requisição inválida.", body.get("mensagem"));
        assertEquals(400, body.get("status"));
        assertNotNull(body.get("timestamp"));
    }

    @Test
    void testHandleErroOMDB() {
        ErroIntegracaoOMDBException ex = new ErroIntegracaoOMDBException("Erro na integração.");
        ResponseEntity<?> response = handler.handleErroOMDB(ex);

        assertEquals(502, response.getStatusCodeValue());

        Map<?, ?> body = (Map<?, ?>) response.getBody();
        assertNotNull(body);
        assertEquals("Erro ao consultar a API OMDB: Erro na integração.", body.get("mensagem"));
        assertEquals(502, body.get("status"));
        assertNotNull(body.get("timestamp"));
    }

    @Test
    void testHandleOutrasExceptions() {
        Exception ex = new RuntimeException("Erro qualquer.");
        ResponseEntity<?> response = handler.handleOutras(ex);

        assertEquals(500, response.getStatusCodeValue());

        Map<?, ?> body = (Map<?, ?>) response.getBody();
        assertNotNull(body);
        assertEquals("Erro interno no servidor.", body.get("mensagem"));
        assertEquals(500, body.get("status"));
        assertNotNull(body.get("timestamp"));
    }
}




