package com.example.controlefilmes.service;

import com.example.controlefilmes.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private List<Usuario> usuarios = new ArrayList<>();

    public UsuarioService() {
        usuarios.add(new Usuario("Administrador", "adm", "adm"));
    }

    public Usuario autenticar(String email, String senha) {
        return usuarios.stream()
                .filter(u -> u.getEmail().equals(email) && u.getSenha().equals(senha))
                .findFirst().orElse(null);
    }

    public List<Usuario> listar() {
        return usuarios;
    }

    public boolean adicionar(Usuario u) {
        if (usuarios.stream().anyMatch(x -> x.getEmail().equals(u.getEmail())))
            return false;
        usuarios.add(u);
        return true;
    }
}