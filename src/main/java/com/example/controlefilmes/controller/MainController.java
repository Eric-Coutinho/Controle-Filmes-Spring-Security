package com.example.controlefilmes.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.controlefilmes.model.Usuario;

@Controller
public class MainController {
    @GetMapping("/")
    public String main(Model m, HttpSession s) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Map the username to a Usuario object (for demo purposes)
        Usuario user;
        if ("adm".equals(username)) {
            user = new Usuario("Administrador", "adm@example.com", "senha123");
        } else {
            user = new Usuario(username, "", "");
        }

        m.addAttribute("user", user);
        return "main";
    }
}
