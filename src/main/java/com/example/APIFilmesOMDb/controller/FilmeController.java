package com.example.APIFilmesOMDb.controller;

import com.example.APIFilmesOMDb.converter.FilmeConverter;
import com.example.APIFilmesOMDb.dto.FilmeDTO;
import com.example.APIFilmesOMDb.service.FilmeService;
import com.example.APIFilmesOMDb.vo.FilmeOMDB;
import com.example.APIFilmesOMDb.vo.FilmeVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Tag(name = "1. Consultar Filme na OMDb")
    @Operation(
            summary = "Consulta um filme na OMDb",
            description = "Busca detalhes de um filme através do título na API OMDb.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Filme encontrado"),
                    @ApiResponse(responseCode = "400", description = "Título inválido"),
                    @ApiResponse(responseCode = "500", description = "Erro ao integrar com a API OMDb")
            }
    )
    @GetMapping("/omdb/{tema}")
    public ResponseEntity<FilmeOMDB> getFilmeOMDB(@PathVariable String tema) {
        tema = tema.replace("+", " ");
        return ResponseEntity.ok(filmeService.getFilme(tema));
    }

    @Tag(name = "2. Salvar Filme")
    @Operation(
            summary = "Salva um novo filme localmente",
            description = "Grava os dados do filme no banco de dados interno.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Filme salvo com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                    @ApiResponse(responseCode = "500", description = "Erro interno ao salvar")
            }
    )
    @PostMapping
    public ResponseEntity<FilmeVO> saveFilme(@RequestBody FilmeDTO filmeDTO) {
        FilmeVO filmeVO = filmeConverter.converteParaFilmeVO(filmeService.save(filmeDTO));
        addHateoas(filmeVO);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmeVO);
    }

    @Tag(name = "3. Listar Filmes")
    @Operation(
            summary = "Lista todos os filmes salvos localmente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro ao buscar os filmes")
            }
    )
    @GetMapping("/listarFilmes")
    public ResponseEntity<List<FilmeVO>> listarFilmes() {
        List<FilmeVO> filmes = filmeService.listarTodos()
                .stream()
                .map(filmeConverter::converteParaFilmeVO)
                .peek(this::addHateoas) // Aqui adiciona o HATEOAS para cada filme
                .toList();
        return ResponseEntity.ok(filmes);
    }


    @Tag(name = "4. Buscar Filme por ID")
    @Operation(
            summary = "Busca um filme pelo ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Filme encontrado"),
                    @ApiResponse(responseCode = "404", description = "Filme não encontrado")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<FilmeVO> getByid(@PathVariable("id") long id) {
        FilmeVO filmeVO = filmeConverter.converteParaFilmeVO(filmeService.getById(id));
        return ResponseEntity.ok(filmeVO);
    }

    @Tag(name = "5. Deletar Filme")
    @Operation(
            summary = "Deleta um filme pelo ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Filme deletado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Filme não encontrado")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFilme(@PathVariable("id") long id) {
        filmeService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    private void addHateoas(FilmeVO filmeVO) {
        filmeVO.add(linkTo(methodOn(FilmeController.class).getByid(filmeVO.getId()))
                .withSelfRel());
    }
}
