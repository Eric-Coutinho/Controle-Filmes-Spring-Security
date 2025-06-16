package com.example.controlefilmes.controller;
import com.example.controlefilmes.model.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class MainController {
    @GetMapping("/")
    public String main(Model m, HttpSession s) {
        Usuario u = (Usuario) s.getAttribute("usuario");
        if (u == null) return "redirect:/login";
        m.addAttribute("user",u);
        return "main";
    }
}