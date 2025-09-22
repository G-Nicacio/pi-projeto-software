package com.example.insper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testSaveAndFind() {
        Book book = new Book(null, "Repo Book", "Repo Author");
        book.setGenero("Ficção");
        book.setAnoPublicacao(2025);
        book.setDataCadastro(LocalDate.now());

        Book saved = bookRepository.save(book);
        assertNotNull(saved.getId());

        Book found = bookRepository.findById(saved.getId()).orElse(null);
        assertNotNull(found);
        assertEquals("Repo Book", found.getTitulo());
        assertEquals("Repo Author", found.getAutor());
    }

    @Test
    void testUpdate() {
        Book b = bookRepository.save(new Book(null, "Old", "A"));
        b.setTitulo("New");
        Book updated = bookRepository.save(b);
        assertEquals("New", updated.getTitulo());
    }

    @Test
    void testDelete() {
        Book b = bookRepository.save(new Book(null, "Temp", "X"));
        Long id = b.getId();
        bookRepository.deleteById(id);
        assertTrue(bookRepository.findById(id).isEmpty());
    }

    @Test
    void testFindAll() {
        bookRepository.save(new Book(null, "1", "A"));
        bookRepository.save(new Book(null, "2", "B"));
        List<Book> all = bookRepository.findAll();
        assertTrue(all.size() >= 2);
    }
}
