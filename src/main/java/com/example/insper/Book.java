package com.example.insper;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Campos que já existiam (em PT)
    private String titulo;
    private String autor;
    private String genero;
    private Integer anoPublicacao;
    private LocalDate dataCadastro = LocalDate.now();

    // ===== Construtores exigidos pelos testes =====
    public Book() {} // no-args (JPA e testes pedem)

    // (Long id, String title, String author)
    public Book(Long id, String title, String author) {
        this.id = id;
        this.titulo = title;
        this.autor = author;
        this.dataCadastro = LocalDate.now();
    }

    // (String title, String author)
    public Book(String title, String author) {
        this.titulo = title;
        this.autor = author;
        this.dataCadastro = LocalDate.now();
    }

    // ===== Getters/Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; } // útil nos testes/mocks

    // Os testes chamam getTitle(), então providenciamos um alias:
    public String getTitle() { return titulo; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public Integer getAnoPublicacao() { return anoPublicacao; }
    public void setAnoPublicacao(Integer anoPublicacao) { this.anoPublicacao = anoPublicacao; }

    public LocalDate getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDate dataCadastro) { this.dataCadastro = dataCadastro; }
}
