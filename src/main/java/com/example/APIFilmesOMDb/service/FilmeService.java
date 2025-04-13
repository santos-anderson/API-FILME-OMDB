package com.example.APIFilmesOMDb.service;

import com.example.APIFilmesOMDb.Client.FilmeClienOMDBFeign;
import com.example.APIFilmesOMDb.converter.FilmeConverter;
import com.example.APIFilmesOMDb.dto.FilmeDTO;
import com.example.APIFilmesOMDb.model.Filme;
import com.example.APIFilmesOMDb.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vo.FilmeOMDB;

import java.util.Optional;

@Service
public class FilmeService {

    @Value("${imdb.apikey}")
    private String apiKey;

    private final FilmeClienOMDBFeign filmeClientFeign;
    private final FilmeRepository filmeRepository;
    private final FilmeConverter filmeConverter;

    public FilmeService(FilmeClienOMDBFeign filmeClientFeign, FilmeRepository filmeRepository, FilmeConverter filmeConverter) {
        this.filmeClientFeign = filmeClientFeign;
        this.filmeRepository = filmeRepository;
        this.filmeConverter = filmeConverter;
    }


    public FilmeOMDB getFilme(String tema) {
        if (tema == null || tema.isEmpty()) {
            throw new IllegalArgumentException("O título do filme (tema) não pode ser nulo ou vazio");
        }
        return filmeClientFeign.getFilme(tema, apiKey);
    }


    public Filme save(FilmeDTO filmeDTO) {
        if (filmeDTO == null) {
            throw new IllegalArgumentException("FilmeDTO não pode ser nulo");
        }

        Filme filme = filmeConverter.converteParaFilme(filmeDTO);
        return filmeRepository.save(filme);
    }


    public Filme getById(long id) {
        Optional<Filme> filme = filmeRepository.findById(id);
        return filme.orElseThrow(() -> new IllegalArgumentException("Filme não encontrado com o ID: " + id));
    }
}



