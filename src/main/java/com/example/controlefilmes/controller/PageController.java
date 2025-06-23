package com.example.controlefilmes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String mainPage() {
        return "main";
    }

    @GetMapping("/filmes/novo")
    public String filmesPage() {
        return "filmes";
    }

    @GetMapping("/usuarios/cadastro")
    public String usuariosPage() {
        return "usuarios";
    }
}
