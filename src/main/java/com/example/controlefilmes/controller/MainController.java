package com.example.controlefilmes.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.controlefilmes.model.Usuario;
import com.example.controlefilmes.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class MainController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/api/user")
    public Usuario main() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Usuario user = usuarioService.buscarComFilmesPorNome(username);

        System.out.println("User fetched: " + user.getNome());
        System.out.println("Para Assistir size: " + (user.getParaAssistir() != null ? user.getParaAssistir().size() : "null"));
        System.out.println("Assistidos size: " + (user.getAssistidos() != null ? user.getAssistidos().size() : "null"));

        return user;
    }
}
