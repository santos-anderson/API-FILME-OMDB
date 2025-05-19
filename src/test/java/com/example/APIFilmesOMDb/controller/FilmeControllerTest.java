package com.example.APIFilmesOMDb.controller;
import com.example.APIFilmesOMDb.converter.FilmeConverter;
import com.example.APIFilmesOMDb.dto.FilmeDTO;
import com.example.APIFilmesOMDb.model.Filme;
import com.example.APIFilmesOMDb.service.FilmeService;
import com.example.APIFilmesOMDb.vo.FilmeOMDB;
import com.example.APIFilmesOMDb.vo.FilmeVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FilmeController.class)
public class FilmeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmeService filmeService;

    @MockBean
    private FilmeConverter filmeConverter;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testGetFilmeOMDB() throws Exception {
        FilmeOMDB mockOMDB = new FilmeOMDB();
        mockOMDB.setTitle("Inception");
        mockOMDB.setYear("2010");

        Mockito.when(filmeService.getFilme("Inception")).thenReturn(mockOMDB);

        mockMvc.perform(get("/filme/omdb/Inception"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Title").value("Inception"))
                .andExpect(jsonPath("$.Year").value("2010"));
    }


    @Test
    public void testSaveFilme() throws Exception {
        FilmeDTO dto = new FilmeDTO();
        dto.setTitle("Inception");
        dto.setYear("2010");

        Filme filme = new Filme();
        filme.setId(1L);
        filme.setTitle("Inception");
        filme.setYear("2010");

        FilmeVO vo = new FilmeVO();
        vo.setId(1L);
        vo.setTitle("Inception");
        vo.setYear("2010");

        Mockito.when(filmeService.save(any(FilmeDTO.class))).thenReturn(filme); // <-- aqui está a correção
        Mockito.when(filmeConverter.converteParaFilmeVO(filme)).thenReturn(vo);

        mockMvc.perform(post("/filme")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Inception"))
                .andExpect(jsonPath("$.year").value("2010"));
    }

    @Test
    public void testListarFilmes() throws Exception {

        Filme filme = new Filme();
        filme.setId(1L);
        filme.setTitle("Inception");
        filme.setYear("2010");


        FilmeVO vo = new FilmeVO();
        vo.setId(1L);
        vo.setTitle("Inception");
        vo.setYear("2010");

        Mockito.when(filmeService.listarTodos()).thenReturn(List.of(filme));
        Mockito.when(filmeConverter.converteParaFilmeVO(filme)).thenReturn(vo);

        mockMvc.perform(get("/filme/listarFilmes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Inception"))
                .andExpect(jsonPath("$[0].year").value("2010"));
    }

    @Test
    public void testGetById() throws Exception {
        Filme filme = new Filme(); // Entidade correta
        filme.setId(1L);
        filme.setTitle("Inception");
        filme.setYear("2010");

        FilmeVO vo = new FilmeVO(); // VO retornado pela controller
        vo.setId(1L);
        vo.setTitle("Inception");
        vo.setYear("2010");

        Mockito.when(filmeService.getById(1L)).thenReturn(filme);
        Mockito.when(filmeConverter.converteParaFilmeVO(filme)).thenReturn(vo);

        mockMvc.perform(get("/filme/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Inception"))
                .andExpect(jsonPath("$.year").value("2010"));
    }


    @Test
    public void testDeletarFilme() throws Exception {
        doNothing().when(filmeService).deletarPorId(1L);

        mockMvc.perform(delete("/filme/1"))
                .andExpect(status().isNoContent());
    }
}
