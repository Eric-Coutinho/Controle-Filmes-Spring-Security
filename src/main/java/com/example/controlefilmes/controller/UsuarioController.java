package com.example.controlefilmes.controller;
import com.example.controlefilmes.model.Usuario;
import com.example.controlefilmes.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;
    public UsuarioController(UsuarioService us) { this.usuarioService = us; }
    @GetMapping("/novo")
    public String form(Model m,HttpSession s) {
        if(s.getAttribute("usuario")==null) return "redirect:/login";
        return "usuarios";
    }
    @PostMapping("/add")
    public String add(@RequestParam String nome,@RequestParam String email,@RequestParam String senha, Model m) {
        Usuario u = new Usuario(nome,email,senha);
        if(!usuarioService.adicionar(u)) m.addAttribute("erro","Email já cadastrado");
        return "redirect:/";
    }
}