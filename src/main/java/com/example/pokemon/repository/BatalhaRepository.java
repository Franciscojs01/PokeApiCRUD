package com.example.pokemon.repository;

import com.example.pokemon.domain.Batalha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BatalhaRepository extends JpaRepository<Batalha, Long> {

}
