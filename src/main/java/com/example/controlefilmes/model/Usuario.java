package com.example.controlefilmes.model;
import java.util.ArrayList;
import java.util.List;
public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private List<Filme> paraAssistir = new ArrayList<>();
    private List<Filme> assistidos = new ArrayList<>();
    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public List<Filme> getParaAssistir() { return paraAssistir; }
    public List<Filme> getAssistidos() { return assistidos; }
}