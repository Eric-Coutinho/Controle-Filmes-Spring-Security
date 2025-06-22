package com.example.controlefilmes.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.controlefilmes.model.Filme;
import com.example.controlefilmes.repository.FilmeRepository;

import java.util.List;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    public boolean adicionar(List<Filme> lista, Filme f) {
        boolean notExists = lista.stream().noneMatch(x -> x.getNome().equalsIgnoreCase(f.getNome()));
        if (notExists) {
            filmeRepository.save(f);
            lista.add(f);
            return true;
        }
        return false;
    }

    public void moverParaAssistidos(List<Filme> paraAssistir, List<Filme> assistidos, String nome, String data) {
        for (Filme f : paraAssistir) {
            if (f.getNome().equalsIgnoreCase(nome)) {
                f.setDataAssistido(data);
                filmeRepository.save(f);
                assistidos.add(f);
                paraAssistir.remove(f);
                break;
            }
        }
    }
}
