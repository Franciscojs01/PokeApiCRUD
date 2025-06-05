package com.example.pokemon.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Batalha {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "jogador_id1")
    private Jogador jogador1;

    @ManyToOne
    @JoinColumn(name = "jogador_id2")
    private Jogador jogador2;

    @Enumerated(EnumType.STRING)
    private EstadoBatalha estado = EstadoBatalha.PRONTA_PARA_BATALHA;

    private LocalDate dataCriacao;

    public Batalha() {
    }

    public Batalha(Jogador jogador1, Jogador jogador2, EstadoBatalha estado, LocalDate dataCriacao) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.estado = estado;
        this.dataCriacao = dataCriacao;
    }

    public Long getId() {
        return id;
    }

    public Jogador getJogador2() {
        return jogador2;
    }

    public void setJogador2(Jogador jogador2) {
        this.jogador2 = jogador2;
    }

    public Jogador getJogador1() {
        return jogador1;
    }

    public void setJogador1(Jogador jogador1) {
        this.jogador1 = jogador1;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstadoBatalha getEstado() {
        return estado;
    }

    public void setEstado(EstadoBatalha estado) {
        this.estado = estado;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {Add commentMore actions
        this.dataCriacao = dataCriacao;
    }
}
