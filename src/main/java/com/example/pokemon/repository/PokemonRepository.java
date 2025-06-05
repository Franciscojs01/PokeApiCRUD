package com.example.pokemon.repository;


import com.example.pokemon.domain.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    Long countByJogadorId_JogadorId(Long JogadorId);
}
