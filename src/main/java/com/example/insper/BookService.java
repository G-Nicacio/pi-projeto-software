package com.example.insper;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    // Os testes instanciam new BookService(repo)
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Os testes chamam addBook(Book)
    public Book addBook(Book book) {
        book.setDataCadastro(java.time.LocalDate.now());
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Os testes esperam Book (não Optional) e assinatura (long)
    public Book findBookById(long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
