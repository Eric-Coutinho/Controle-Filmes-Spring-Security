package com.example.controlefilmes.model;
public class Filme {
    private String nome;
    private String genero;
    private int ano;
    private String dataAssistido;
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }
    public String getDataAssistido() { return dataAssistido; }
    public void setDataAssistido(String dataAssistido) { this.dataAssistido = dataAssistido; }
}