package com.example.controlefilmes.repository;

import com.example.controlefilmes.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNome(String nome);
    Optional<Usuario> findByEmail(String email);

    @Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.paraAssistir LEFT JOIN FETCH u.assistidos WHERE u.nome = :nome")
    Optional<Usuario> findByNomeWithFilmes(@Param("nome") String nome);
}
