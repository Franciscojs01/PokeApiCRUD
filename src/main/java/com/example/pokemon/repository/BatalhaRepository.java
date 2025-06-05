package com.example.pokemon.repository;

import com.example.pokemon.domain.Batalha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface BatalhaRepository extends JpaRepository<Batalha, Long> {
    @Query("SELECT COUNT(b) FROM Batalha b WHERE b.jogador1.id = :idJogador and b.estado = 'PRONTA_PARA_BATALHA'")
    Long countByJogador1Id(@Param("idJogador") Long idJogador);

    @Query("SELECT COUNT(b) FROM Batalha b WHERE b.jogador2.id = :idJogador and b.estado = 'PRONTA_PARA_BATALHA'")
    Long countByJogador2Id(@Param("idJogador") Long idJogador);
}
