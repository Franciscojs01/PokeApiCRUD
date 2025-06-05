package com.example.pokemon.dto;

public class PokemonDTO {
    private String nome;
    private String tipo;
    private int nivel;
    private int hp;
    private int ataque;
    private int defesa;

    public PokemonDTO( String nome, String tipo, int nivel, int hp, int ataque, int defesa) {
        this.nome = nome;
        this.tipo = tipo;
        this.nivel = nivel;
        this.hp = hp;
        this.ataque = ataque;
        this.defesa = defesa;
    }

    public PokemonDTO() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }
}