package com.example.APIFilmesOMDb.exception;

public class FilmeNaoEncontradoException extends RuntimeException {
    public FilmeNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
