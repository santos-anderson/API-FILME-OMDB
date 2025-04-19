package com.example.APIFilmesOMDb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class FilmeExceptionHandler {

    @ExceptionHandler(FilmeNaoEncontradoException.class)
    public ResponseEntity<?> handleFilmeNaoEncontrado(FilmeNaoEncontradoException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RequisicaoInvalidaException.class)
    public ResponseEntity<?> handleRequisicaoInvalida(RequisicaoInvalidaException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ErroIntegracaoOMDBException.class)
    public ResponseEntity<?> handleErroOMDB(ErroIntegracaoOMDBException ex) {
        return buildResponse("Erro ao consultar a API OMDB: " + ex.getMessage(), HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleOutras(Exception ex) {
        return buildResponse("Erro interno no servidor.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<?> buildResponse(String mensagem, HttpStatus status) {
        Map<String, Object> erro = new HashMap<>();
        erro.put("timestamp", LocalDateTime.now());
        erro.put("status", status.value());
        erro.put("mensagem", mensagem);
        return new ResponseEntity<>(erro, status);
    }
}
