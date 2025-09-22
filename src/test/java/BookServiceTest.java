package com.example.insper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Test
    void testAddBook_setsDataCadastroAndSaves() {
        BookRepository repo = mock(BookRepository.class);
        BookService service = new BookService(repo);

        Book input = new Book(null, "Title", "Author");
        // mock retornando com id
        Book persisted = new Book(1L, "Title", "Author");
        persisted.setDataCadastro(LocalDate.now());

        when(repo.save(any(Book.class))).thenReturn(persisted);

        Book saved = service.addBook(input);
        assertNotNull(saved.getId());
        assertEquals("Title", saved.getTitulo());
        verify(repo).save(any(Book.class));
    }

    @Test
    void testFindBookById_found() {
        BookRepository repo = mock(BookRepository.class);
        BookService service = new BookService(repo);
        Book b = new Book(10L, "T", "A");
        when(repo.findById(10L)).thenReturn(Optional.of(b));

        Book found = service.findBookById(10L);
        assertNotNull(found);
        assertEquals(10L, found.getId());
        verify(repo).findById(10L);
    }

    @Test
    void testFindBookById_notFoundReturnsNull() {
        BookRepository repo = mock(BookRepository.class);
        BookService service = new BookService(repo);
        when(repo.findById(99L)).thenReturn(Optional.empty());

        Book found = service.findBookById(99L);
        assertNull(found);
        verify(repo).findById(99L);
    }

    @Test
    void testGetAllBooks() {
        BookRepository repo = mock(BookRepository.class);
        BookService service = new BookService(repo);

        when(repo.findAll()).thenReturn(List.of(
                new Book(1L, "A", "X"),
                new Book(2L, "B", "Y")
        ));

        List<Book> all = service.getAllBooks();
        assertEquals(2, all.size());
        verify(repo).findAll();
    }

    @Test
    void testDeleteBook() {
        BookRepository repo = mock(BookRepository.class);
        BookService service = new BookService(repo);

        service.deleteBook(5L);
        verify(repo).deleteById(5L);
    }
}
