package com.example.pokemon.dto;

import com.example.pokemon.domain.EstadoBatalha;

import java.time.LocalDate;


public class BatalhaDTO {
    private JogadorBatalhaDTO jogador1;
    private JogadorBatalhaDTO jogador2;
    private LocalDate dataBatalha;
    private EstadoBatalha estadoBatalha;


    public BatalhaDTO(JogadorBatalhaDTO jogador1, JogadorBatalhaDTO jogador2, LocalDate dataBatalha, EstadoBatalha estadoBatalha) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.dataBatalha = dataBatalha;
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

    public LocalDate getDataBatalha() {
        return dataBatalha;
    }

    public void setDataBatalha(LocalDate dataBatalha) {
        this.dataBatalha = dataBatalha;
    }

    public EstadoBatalha getEstadoBatalha() {
        return estadoBatalha;
    }

    public void setEstadoBatalha(EstadoBatalha estadoBatalha) {
        this.estadoBatalha = estadoBatalha;
    }
}

