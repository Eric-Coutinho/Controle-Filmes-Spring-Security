package com.example.controlefilmes.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.controlefilmes.model.Usuario;
import com.example.controlefilmes.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class MainController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String main(Model m, HttpSession s) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Usuario user = usuarioService.listar().stream()
                .filter(u -> u.getNome().equals(username))
                .findFirst()
                .orElse(new Usuario(username, "", ""));

        s.setAttribute("usuario", user);
        m.addAttribute("user", user);
        return "main";
    }
}
