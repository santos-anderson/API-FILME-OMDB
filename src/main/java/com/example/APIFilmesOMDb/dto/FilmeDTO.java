package com.example.APIFilmesOMDb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados de entrada de um filme")
public class FilmeDTO {

    @JsonProperty("Title")
    @Schema(description = "Título do filme", example = "Inception")
    private String title;

    @JsonProperty("Year")
    @Schema(description = "Ano de lançamento do filme", example = "2010")
    private String year;

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

