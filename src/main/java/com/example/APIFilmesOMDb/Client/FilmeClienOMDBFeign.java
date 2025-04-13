package com.example.APIFilmesOMDb.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vo.FilmeOMDB;

@FeignClient(name = "omdbFilmes", url = "http://www.omdbapi.com/")
public interface FilmeClienOMDBFeign {

    @GetMapping
    FilmeOMDB getFilme(
            @RequestParam("t") String tema,
            @RequestParam("apiKey") String key
    );
}
