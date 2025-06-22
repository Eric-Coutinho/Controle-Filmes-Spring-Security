package com.example.controlefilmes.config;

import com.example.controlefilmes.model.Usuario;
import com.example.controlefilmes.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (usuarioRepository.count() == 0) {
                Usuario adm = new Usuario("adm", "adm", passwordEncoder.encode("adm"));
                Usuario teste = new Usuario("teste", "test@email", passwordEncoder.encode("123"));
                usuarioRepository.save(adm);
                usuarioRepository.save(teste);
            }
        };
    }
}
