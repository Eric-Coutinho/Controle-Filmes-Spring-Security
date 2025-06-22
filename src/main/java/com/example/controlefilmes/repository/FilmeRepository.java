package com.example.controlefilmes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.controlefilmes.model.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {
}
