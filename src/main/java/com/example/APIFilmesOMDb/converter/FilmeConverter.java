package com.example.APIFilmesOMDb.converter;

import com.example.APIFilmesOMDb.dto.FilmeDTO;
import com.example.APIFilmesOMDb.model.Filme;
import org.springframework.stereotype.Component;
import vo.FilmeVO;

@Component

public class FilmeConverter {

    public Filme converteParaFilme(FilmeDTO filmeDTO){
        Filme filme = new Filme();
        filme.setTitle(filmeDTO.getTitle());
        filme.setYear(filmeDTO.getYear());
        return filme;
    }

    public FilmeVO converteParaFilmeVO(Filme filme){
        FilmeVO filmeVO = new FilmeVO();
        filmeVO.setId(filme.getId());
        filmeVO.setTitle(filme.getTitle());
        filmeVO.setYear(filme.getYear());
        return filmeVO;

    }
}
