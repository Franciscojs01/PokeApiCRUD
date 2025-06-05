package com.example.pokemon.dto;

import com.example.pokemon.domain.EstadoBatalha;

import java.time.LocalDate;

public class BatalhaDTO {
    private JogadorBatalhaDTO jogador1;
    private JogadorBatalhaDTO jogador2;
    private LocalDate data_batalha;
    private EstadoBatalha estadoBatalha;

    public BatalhaDTO(JogadorBatalhaDTO jogador1, JogadorBatalhaDTO jogador2, LocalDate data_batalha, EstadoBatalha estadoBatalha) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.data_batalha = data_batalha;
        this.estadoBatalha = estadoBatalha;
    }

    public JogadorBatalhaDTO getJogador1() {
        return jogador1;
    }

    public void setJogador1(JogadorBatalhaDTO jogador1) {
        this.jogador1 = jogador1;
    }

    public JogadorBatalhaDTO getJogador2() {
        return jogador2;
    }

    public void setJogador2(JogadorBatalhaDTO jogador2) {
        this.jogador2 = jogador2;
    }

    public LocalDate getData_batalha() {
        return data_batalha;
    }

    public void setData_batalha(LocalDate data_batalha) {
        this.data_batalha = data_batalha;
    }

    public EstadoBatalha getEstadoBatalha() {
        return estadoBatalha;
    }

    public void setEstadoBatalha(EstadoBatalha estadoBatalha) {
        this.estadoBatalha = estadoBatalha;
    }
}

