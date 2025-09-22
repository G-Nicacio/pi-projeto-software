package com.example.insper;

import com.example.insper.BookService;
import com.example.insper.BookRepository;
import com.example.insper.Book;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

public class BookServiceTest {

    @Test
    void testAddBook() {
        BookRepository repo = Mockito.mock(BookRepository.class);
        BookService service = new BookService(repo);
        Book book = new Book(null, "Title", "Author");
        Mockito.when(repo.save(book)).thenReturn(new Book(1L, "Title", "Author"));
        Book saved = service.addBook(book);
        assertNotNull(saved.getId());
        assertEquals("Title", saved.getTitle());
    }

    @Test
    void testFindBookById() {
        BookRepository repo = Mockito.mock(BookRepository.class);
        BookService service = new BookService(repo);
        Book book = new Book(1L, "Title", "Author");
        Mockito.when(repo.findById(1L)).thenReturn(java.util.Optional.of(book));
        Book found = service.findBookById(1L);
        assertEquals("Title", found.getTitle());
    }
}