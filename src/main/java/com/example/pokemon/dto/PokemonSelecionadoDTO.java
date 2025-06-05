package com.example.pokemon.dto;

import java.util.List;

public class PokemonSelecionadoDTO {
    private List<PokemonEscolhaDTO> pokemonEscolhidos;

    public PokemonSelecionadoDTO (List<PokemonEscolhaDTO> pokemonEscolhidos) {

        this.pokemonEscolhidos = pokemonEscolhidos;
    }

    public List<PokemonEscolhaDTO> getPokemonEscolhidos() {
        return pokemonEscolhidos;
    }

    public void setPokemonEscolhidos(List<PokemonEscolhaDTO> pokemonEscolhidos) {
        this.pokemonEscolhidos = pokemonEscolhidos;
    }
}
