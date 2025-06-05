package com.example.pokemon.dto;

import java.time.LocalDate;

public class BatalhaCadastroDTO {
    private Long jogador1;
    private Long jogador2;
    private Long estado;
    private LocalDate dataCadastro;

    public BatalhaCadastroDTO(Long jogador1, Long jogador2, Long estado, LocalDate dataCadastro) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.estado = estado;
        this.dataCadastro = dataCadastro;
    }

    public Long getJogador1() {
        return jogador1;
    }

    public void setJogador1(Long jogador1) {
        this.jogador1 = jogador1;
    }

    public Long getJogador2() {
        return jogador2;
    }

    public void setJogador2(Long jogador2) {
        this.jogador2 = jogador2;
    }

    public Long getEstado() {
        return estado;
    }

    public void setEstado(Long estado) {
        this.estado = estado;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}