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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService {

    @Value("${imdb.apikey}")
    private String apiKey;

    private final FilmeClientOMDBFeign filmeClientFeign;
    private final FilmeRepository filmeRepository;
    private final FilmeConverter filmeConverter;

    public FilmeService(FilmeClientOMDBFeign filmeClientFeign,
                        FilmeRepository filmeRepository,
                        FilmeConverter filmeConverter) {
        this.filmeClientFeign = filmeClientFeign;
        this.filmeRepository = filmeRepository;
        this.filmeConverter = filmeConverter;
    }


    public FilmeOMDB getFilme(String tema) {
        if (tema == null || tema.trim().isEmpty()) {
            throw new RequisicaoInvalidaException("O título do filme (tema) não pode ser nulo ou vazio.");
        }

        try {
            return filmeClientFeign.getFilme(tema, apiKey);
        } catch (Exception e) {
            throw new ErroIntegracaoOMDBException("Erro ao consultar a API OMDB: " + e.getMessage());
        }
    }


    public Filme save(FilmeDTO filmeDTO) {
        if (filmeDTO == null) {
            throw new RequisicaoInvalidaException("FilmeDTO não pode ser nulo.");
        }

        Filme filme = filmeConverter.converteParaFilme(filmeDTO);
        return filmeRepository.save(filme);
    }


    public Filme getById(long id) {
        return filmeRepository.findById(id)
                .orElseThrow(() -> new FilmeNaoEncontradoException("Filme não encontrado com o ID: " + id));
    }


    public List<Filme> listarTodos() {
        return filmeRepository.findAll();
    }


    public void deletarPorId(long id) {
        if (!filmeRepository.existsById(id)) {
            throw new FilmeNaoEncontradoException("Não foi possível deletar. Filme com ID " + id + " não encontrado.");
        }
        filmeRepository.deleteById(id);
    }
}





