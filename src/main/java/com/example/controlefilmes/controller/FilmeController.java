package com.example.controlefilmes.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.controlefilmes.model.Filme;
import com.example.controlefilmes.model.Usuario;
import com.example.controlefilmes.service.FilmeService;
import com.example.controlefilmes.service.UsuarioService;

import java.time.LocalDate;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/filmes")
public class FilmeController {
    private final FilmeService filmeService;

    @Autowired
    private UsuarioService usuarioService;

    public FilmeController(FilmeService fs) {
        this.filmeService = fs;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Map<String, Object> payload) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Usuario u = usuarioService.listar().stream()
                .filter(user -> user.getNome().equals(username))
                .findFirst()
                .orElse(null);
        if (u == null) {
            return ResponseEntity.status(401).body("Usuário não autenticado");
        }
        String nome = (String) payload.get("nome");
        String genero = (String) payload.get("genero");
        int ano = (int) payload.get("ano");
        Filme f = new Filme();
        f.setNome(nome);
        f.setGenero(genero);
        f.setAno(ano);
        if (!filmeService.adicionar(u.getParaAssistir(), f, u)) {
            return ResponseEntity.badRequest().body("Filme já existe na lista");
        }
        return ResponseEntity.ok("Filme adicionado com sucesso");
    }

    @PostMapping("/assistido")
    public ResponseEntity<?> assistido(@RequestBody Map<String, String> payload) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Usuario u = usuarioService.listar().stream()
                .filter(user -> user.getNome().equals(username))
                .findFirst()
                .orElse(null);
        if (u == null) {
            return ResponseEntity.status(401).body("Usuário não autenticado");
        }
        String nome = payload.get("nome");
        filmeService.moverParaAssistidos(u.getParaAssistir(), u.getAssistidos(), nome, LocalDate.now().toString());
        return ResponseEntity.ok("Filme marcado como assistido");
    }

    @PostMapping("/remover")
    public ResponseEntity<?> remover(@RequestBody Map<String, String> payload) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Usuario u = usuarioService.listar().stream()
                .filter(user -> user.getNome().equals(username))
                .findFirst()
                .orElse(null);
        if (u == null) {
            return ResponseEntity.status(401).body("Usuário não autenticado");
        }
        String nome = payload.get("nome");
        u.getParaAssistir().removeIf(f -> f.getNome().equalsIgnoreCase(nome));
        return ResponseEntity.ok("Filme removido da lista");
    }

    @GetMapping("/all")
    public List<Filme> getAllFilmes() {
        return filmeService.listar();
    }
}
