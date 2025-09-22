package com.example.insper;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookEntityTest {

    @Test
    void testGettersSettersAndDefaults() {
        Book b = new Book();
        assertNull(b.getId());
        // dataCadastro default (constructor no-args pode não setar automaticamente se já está como field init)
        assertNotNull(b.getDataCadastro());

        b.setTitulo("T");
        b.setAutor("A");
        b.setGenero("G");
        b.setAnoPublicacao(2025);
        LocalDate now = LocalDate.now();
        b.setDataCadastro(now);

        assertEquals("T", b.getTitulo());
        assertEquals("A", b.getAutor());
        assertEquals("G", b.getGenero());
        assertEquals(2025, b.getAnoPublicacao());
        assertEquals(now, b.getDataCadastro());
    }

    @Test
    void testConvenienceConstructor() {
        Book b = new Book(null, "X", "Y");
        assertEquals("X", b.getTitulo());
        assertEquals("Y", b.getAutor());
        assertNotNull(b.getDataCadastro());
    }
}
