package com.example.APIFilmesOMDb.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vo.FilmeOMDB;

@FeignClient(value= "omdbFilmes", url ="http://www.omdbapi.com/")

public interface FilmeClienOMDBFeign {

    @RequestMapping(method = RequestMethod.GET)
    FilmeOMDB getFilme(@RequestParam("t") String tema, @RequestParam("apiKey") String key);

}
