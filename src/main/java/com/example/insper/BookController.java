package com.example.insper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping
    public Book cadastrarLivro(@RequestBody Book book) {
        book.setDataCadastro(java.time.LocalDate.now());
        return bookRepository.save(book);
    }

    @GetMapping
    public List<Book> listarLivros() {
        return bookRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void excluirLivro(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }
}
