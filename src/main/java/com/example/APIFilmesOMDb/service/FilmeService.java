package com.example.APIFilmesOMDb.service;


import com.example.APIFilmesOMDb.Client.FilmeClienOMDBFeign;
import com.example.APIFilmesOMDb.converter.FilmeConverter;
import com.example.APIFilmesOMDb.dto.FilmeDTO;
import com.example.APIFilmesOMDb.model.Filme;
import com.example.APIFilmesOMDb.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vo.FilmeOMDB;

@Service

public class FilmeService {

    @Value("${imdb.apikey}")
    private String apiKey;

    private FilmeClienOMDBFeign filmeClientFeign;


    private FilmeRepository filmeRepository;

    private FilmeConverter filmeConverter;

    public FilmeService(FilmeClienOMDBFeign filmeClientFeign, FilmeRepository filmeRepository, FilmeConverter filmeConverter) {
        this.filmeClientFeign = filmeClientFeign;
        this.filmeRepository = filmeRepository;
        this.filmeConverter = filmeConverter;
    }

    public FilmeOMDB getFilme(String tema) {
            return filmeClientFeign.getFilme(tema, apiKey);
        }

        public Filme save(FilmeDTO filmeDTO){
        Filme filme = filmeConverter.converteParaFilme(filmeDTO);
        return filmeRepository.save(filme);
        }

        public Filme getById(long id){
        return filmeRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("Filme not found"));
        }
}


