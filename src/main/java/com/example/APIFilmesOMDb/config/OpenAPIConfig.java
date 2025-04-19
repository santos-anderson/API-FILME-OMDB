package com.example.APIFilmesOMDb.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.tags.Tag;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Filmes OMDb")
                        .description("Documentação da API de Filmes integrada com a OMDb")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Anderson Santos")
                                .email("santos.anders@gmail.com")
                                .url("https://github.com/santos-anderson"))
                );
    }

    @Bean
    public OpenApiCustomizer sortTags() {
        return openApi -> {
            List<Tag> tags = Arrays.asList(
                    new Tag().name("1. Consultar Filme na OMDb"),
                    new Tag().name("2. Salvar Filme"),
                    new Tag().name("3. Listar Filmes"),
                    new Tag().name("4. Buscar Filme por ID"),
                    new Tag().name("5. Deletar Filme")
            );
            openApi.setTags(tags);
        };
    }
}




