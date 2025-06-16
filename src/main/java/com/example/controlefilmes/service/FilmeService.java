package com.example.controlefilmes.service;
import com.example.controlefilmes.model.Filme;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class FilmeService {
    public boolean adicionar(List<Filme> lista, Filme f) {
        return lista.stream().noneMatch(x->x.getNome().equalsIgnoreCase(f.getNome()))
            && lista.add(f);
    }
    public void moverParaAssistidos(List<Filme> paraAssistir, List<Filme> assistidos, String nome, String data) {
        for (Filme f: paraAssistir) {
            if (f.getNome().equalsIgnoreCase(nome)) {
                f.setDataAssistido(data);
                assistidos.add(f);
                paraAssistir.remove(f);
                break;
            }
        }
    }
}