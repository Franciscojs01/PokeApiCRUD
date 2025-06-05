package com.example.pokemon.repository;


import com.example.pokemon.domain.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Long> {
    Optional<Jogador> findByEmail(String email);
}
