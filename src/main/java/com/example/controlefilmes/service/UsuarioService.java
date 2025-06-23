package com.example.controlefilmes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.controlefilmes.model.Usuario;
import com.example.controlefilmes.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario autenticar(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email).orElse(null);
        if (usuario != null && passwordEncoder.matches(senha, usuario.getSenha())) {
            return usuario;
        }
        return null;
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarComFilmesPorNome(String nome) {
        return usuarioRepository.findByNomeWithFilmes(nome).orElse(null);
    }

    public boolean adicionar(Usuario u) {
        if (usuarioRepository.findByEmail(u.getEmail()).isPresent()) {
            return false;
        }
        u.setSenha(passwordEncoder.encode(u.getSenha()));
        usuarioRepository.save(u);
        return true;
    }
}
