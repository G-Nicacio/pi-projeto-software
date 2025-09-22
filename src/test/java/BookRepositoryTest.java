package com.example.insper;

import org.junit.jupiter.api.Test;
import com.example.insper.BookRepository;
import com.example.insper.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testSaveAndFind() {
        Book book = new Book(null, "Repo Book", "Repo Author");
        Book saved = bookRepository.save(book);
        assertNotNull(saved.getId());
        Book found = bookRepository.findById(saved.getId()).orElse(null);
        assertNotNull(found);
        assertEquals("Repo Book", found.getTitle());
    }
}