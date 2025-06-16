package com.example.controlefilmes.controller;

import com.example.controlefilmes.service.UsuarioService;
import com.example.controlefilmes.model.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String senha, Model m, HttpSession s) {
        Usuario u = usuarioService.autenticar(email, senha);
        if (u == null) {
            m.addAttribute("erro", "Credenciais inválidas");
            return "login";
        }
        s.setAttribute("usuario", u);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession s) {
        s.invalidate();
        return "redirect:/login";
    }
}