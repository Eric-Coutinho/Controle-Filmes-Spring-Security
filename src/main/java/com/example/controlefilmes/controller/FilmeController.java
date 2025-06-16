package com.example.controlefilmes.controller;
import com.example.controlefilmes.model.Filme;
import com.example.controlefilmes.model.Usuario;
import com.example.controlefilmes.service.FilmeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
@Controller
@RequestMapping("/filmes")
public class FilmeController {
    private final FilmeService filmeService;
    public FilmeController(FilmeService fs) { this.filmeService = fs; }
    @GetMapping("/novo")
    public String novoForm(Model m,HttpSession s) {
        if(s.getAttribute("usuario")==null) return "redirect:/login";
        return "filmes";
    }
    @PostMapping("/add")
    public String add(@RequestParam String nome,@RequestParam String genero,@RequestParam int ano,HttpSession s,Model m) {
        Usuario u = (Usuario) s.getAttribute("usuario");
        Filme f = new Filme(); f.setNome(nome); f.setGenero(genero); f.setAno(ano);
        if(!filmeService.adicionar(u.getParaAssistir(),f)) {
            m.addAttribute("erro","Filme já existe na lista");
        }
        return "redirect:/";
    }
    @PostMapping("/assistido")
    public String assistido(@RequestParam String nome,HttpSession s) {
        Usuario u = (Usuario) s.getAttribute("usuario");
        filmeService.moverParaAssistidos(u.getParaAssistir(),u.getAssistidos(),nome,LocalDate.now().toString());
        return "redirect:/";
    }
    @PostMapping("/remover")
    public String remover(@RequestParam String nome,HttpSession s) {
        Usuario u = (Usuario) s.getAttribute("usuario");
        u.getParaAssistir().removeIf(f->f.getNome().equalsIgnoreCase(nome));
        return "redirect:/";
    }
}