package com.example.pokemon.dto;

import java.util.List;

public class JogadorBatalhaDTO {
    private List<PokemonDTO> pokemon;
    private String nome;

    public JogadorBatalhaDTO(List<PokemonDTO> pokemon, String nome) {
        this.pokemon = pokemon;
        this.nome = nome;
    }

    public List<PokemonDTO> getPokemon() {
        return pokemon;
    }

    public void setPokemon(List<PokemonDTO> pokemon) {
        this.pokemon = pokemon;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}