package com.example.APIFilmesOMDb.controller;

import com.example.APIFilmesOMDb.converter.FilmeConverter;
import com.example.APIFilmesOMDb.dto.FilmeDTO;
import com.example.APIFilmesOMDb.service.FilmeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vo.FilmeOMDB;
import vo.FilmeVO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/filme")
public class FilmeController {

    private final FilmeConverter filmeConverter;
    private final FilmeService filmeService;

    public FilmeController(FilmeConverter filmeConverter, FilmeService filmeService) {
        this.filmeConverter = filmeConverter;
        this.filmeService = filmeService;
    }


    @GetMapping("/omdb/{tema}")
    public ResponseEntity<FilmeOMDB> getFilmeOMDB(@PathVariable String tema) {
        // Substitui '+' por espaço (caso o cliente envie o título com '+')
        tema = tema.replace("+", " ");

        try {
            FilmeOMDB filmeOMDB = filmeService.getFilme(tema);
            return ResponseEntity.status(HttpStatus.OK).body(filmeOMDB);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping
    public ResponseEntity<FilmeVO> saveFilme(@RequestBody FilmeDTO filmeDTO) {
        try {
            FilmeVO filmeVO = filmeConverter.converteParaFilmeVO(filmeService.save(filmeDTO));

            addHateoas(filmeVO);

            return ResponseEntity.status(HttpStatus.CREATED).body(filmeVO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    private void addHateoas(FilmeVO filmeVO) {
        filmeVO.add(linkTo(methodOn(FilmeController.class).getByid(filmeVO.getId()))
                .withSelfRel());
    }


    @GetMapping("/{id}")
    public ResponseEntity<FilmeVO> getByid(@PathVariable("id") long id) {
        try {
            FilmeVO filmeVO = filmeConverter.converteParaFilmeVO(filmeService.getById(id));
            return ResponseEntity.ok(filmeVO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

