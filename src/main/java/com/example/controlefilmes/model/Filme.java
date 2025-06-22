package com.example.controlefilmes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String genero;
    private int ano;
    private String dataAssistido;

    public Filme() {
        // No-arg constructor required by JPA
    }

    public Long getId() {
        return id;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }
    public String getDataAssistido() { return dataAssistido; }
    public void setDataAssistido(String dataAssistido) { this.dataAssistido = dataAssistido; }
}
