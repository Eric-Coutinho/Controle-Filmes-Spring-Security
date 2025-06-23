package com.example.controlefilmes.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;

import com.example.controlefilmes.model.Usuario;
import com.example.controlefilmes.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService us) {
        this.usuarioService = us;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Usuario u) {
        if (!usuarioService.adicionar(u)) {
            return ResponseEntity.badRequest().body("Email já cadastrado");
        }
        return ResponseEntity.ok("Usuário cadastrado com sucesso");
    }

    @GetMapping("/all")
    public List<Usuario> getAllUsers() {
        return usuarioService.listar();
    }
}
