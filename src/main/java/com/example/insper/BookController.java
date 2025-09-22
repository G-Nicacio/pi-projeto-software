package com.example.insper;

import com.example.insper.model.Book;
import com.example.insper.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    // Cadastrar livro
    @PostMapping
    public Book cadastrarLivro(@RequestBody Book book) {
        book.setDataCadastro(java.time.LocalDate.now());
        return bookRepository.save(book);
    }

    // Listar todos os livros
    @GetMapping
    public List<Book> listarLivros() {
        return bookRepository.findAll();
    }

    // Excluir livro
    @DeleteMapping("/{id}")
    public void excluirLivro(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }
}