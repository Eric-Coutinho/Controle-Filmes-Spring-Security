package com.example.controlefilmes.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.controlefilmes.model.Filme;
import com.example.controlefilmes.model.Usuario;
import com.example.controlefilmes.service.FilmeService;
import com.example.controlefilmes.service.UsuarioService;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
@RequestMapping("/filmes")
public class FilmeController {
    private final FilmeService filmeService;

    @Autowired
    private UsuarioService usuarioService;

    public FilmeController(FilmeService fs) {
        this.filmeService = fs;
    }

    @GetMapping("/novo")
    public String novoForm(Model m, HttpSession s) {
        return "filmes";
    }

    @PostMapping("/add")
    public String add(@RequestParam String nome, @RequestParam String genero, @RequestParam int ano, HttpSession s,
            Model m) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Usuario u = usuarioService.listar().stream()
                .filter(user -> user.getNome().equals(username))
                .findFirst()
                .orElse(null);
        if (u == null) {
            m.addAttribute("erro", "Usuário não autenticado");
            return "redirect:/login";
        }
        Filme f = new Filme();
        f.setNome(nome);
        f.setGenero(genero);
        f.setAno(ano);
        if (!filmeService.adicionar(u.getParaAssistir(), f, u)) {
            m.addAttribute("erro", "Filme já existe na lista");
        }
        return "redirect:/";
    }

    @PostMapping("/assistido")
    public String assistido(@RequestParam String nome, HttpSession s) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Usuario u = usuarioService.listar().stream()
                .filter(user -> user.getNome().equals(username))
                .findFirst()
                .orElse(null);
        if (u == null) {
            return "redirect:/login";
        }
        filmeService.moverParaAssistidos(u.getParaAssistir(), u.getAssistidos(), nome, LocalDate.now().toString());
        return "redirect:/";
    }

    @PostMapping("/remover")
    public String remover(@RequestParam String nome, HttpSession s) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Usuario u = usuarioService.listar().stream()
                .filter(user -> user.getNome().equals(username))
                .findFirst()
                .orElse(null);
        if (u == null) {
            return "redirect:/login";
        }
        u.getParaAssistir().removeIf(f -> f.getNome().equalsIgnoreCase(nome));
        return "redirect:/";
    }
}
