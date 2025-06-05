package com.example.pokemon.controller;


import com.example.pokemon.Service.PokemonService;
import com.example.pokemon.dto.PokemonEscolhaDTO;
import com.example.pokemon.dto.PokemonSelecionadoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping("/sorteio")
    public List<PokemonEscolhaDTO> sortearPokemons() {
        return pokemonService.gerarPokemonsParaEscolha();
    }

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastrarEscolhidos(@RequestBody PokemonSelecionadoDTO dto) {
        pokemonService.cadastrarPokemonsEscolhidos(dto);
        return ResponseEntity.ok("Pokémons cadastrados com sucesso!");
    }
}
