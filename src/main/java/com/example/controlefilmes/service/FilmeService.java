
package com.example.controlefilmes.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.controlefilmes.model.Filme;
import com.example.controlefilmes.model.Usuario;
import com.example.controlefilmes.repository.FilmeRepository;
import com.example.controlefilmes.repository.UsuarioRepository;

import java.util.Set;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean adicionar(Set<Filme> lista, Filme f, Usuario usuario) {
        boolean notExists = lista.stream().noneMatch(x -> x.getNome().equalsIgnoreCase(f.getNome()));
        if (notExists) {
            f.setUsuario(usuario);
            filmeRepository.save(f);
            lista.add(f);
            usuarioRepository.save(usuario); // Save user to persist join table changes
            return true;
        }
        return false;
    }

    public void moverParaAssistidos(Set<Filme> paraAssistir, Set<Filme> assistidos, String nome, String data) {
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
