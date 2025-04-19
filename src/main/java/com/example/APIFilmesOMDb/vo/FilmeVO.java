package com.example.APIFilmesOMDb.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.hateoas.RepresentationModel;

@Schema(description = "Visualização do filme cadastrado")
public class FilmeVO extends RepresentationModel<FilmeVO> {

    @Schema(description = "ID do filme", example = "1")
    private long id;

    @Schema(description = "Título do filme", example = "Inception")
    private String title;

    @Schema(description = "Ano do filme", example = "2010")
    private String year;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}

