package com.example.insper;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    @Test
    void testGetBooks_empty() throws Exception {
        when(bookRepository.findAll()).thenReturn(List.of());
        mockMvc.perform(get("/books"))
               .andExpect(status().isOk())
               .andExpect(content().json("[]"));
        verify(bookRepository).findAll();
    }

    @Test
    void testGetBooks_withData() throws Exception {
        Book b = new Book(null, "As Crônicas", "Ash");
        b.setGenero("Ficção");
        b.setAnoPublicacao(2025);
        b.setDataCadastro(LocalDate.now());
        when(bookRepository.findAll()).thenReturn(List.of(b));

        mockMvc.perform(get("/books"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(1)))
               .andExpect(jsonPath("$[0].titulo").value("As Crônicas"))
               .andExpect(jsonPath("$[0].autor").value("Ash"));
    }

    @Test
    void testPost_createsAndSetsDataCadastro() throws Exception {
        // captura o objeto que chega no repo.save
        ArgumentCaptor<Book> captor = ArgumentCaptor.forClass(Book.class);

        Book saved = new Book(1L, "Novo", "Autor");
        saved.setDataCadastro(LocalDate.now());
        when(bookRepository.save(any(Book.class))).thenReturn(saved);

        String body = """
        {
          "titulo": "Novo",
          "autor": "Autor",
          "genero": "Ficção",
          "anoPublicacao": 2025
        }
        """;

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(1));

        verify(bookRepository).save(captor.capture());
        Book toSave = captor.getValue();
        // título/autor foram enviados
        assert "Novo".equals(toSave.getTitulo());
        assert "Autor".equals(toSave.getAutor());
        // controller seta dataCadastro agora
        assert toSave.getDataCadastro() != null;
    }

    @Test
    void testDeleteById() throws Exception {
        mockMvc.perform(delete("/books/7"))
               .andExpect(status().isOk());
        verify(bookRepository).deleteById(7L);
    }
}
