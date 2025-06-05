package com.example.pokemon.Service;

import com.example.pokemon.domain.Jogador;
import com.example.pokemon.domain.Pokemon;
import com.example.pokemon.dto.PokemonDTO;
import com.example.pokemon.dto.PokemonEscolhaDTO;
import com.example.pokemon.dto.PokemonSelecionadoDTO;
import com.example.pokemon.exceptions.JogadorNotFoundException;
import com.example.pokemon.repository.JogadorRepository;
import com.example.pokemon.repository.PokemonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PokemonService {
    @Autowired
    PokeApiService pokeApiService;

    @Autowired
    PokemonRepository pokemonRepository;

    private final Random random = new Random();
    @Autowired
    private JogadorRepository jogadorRepository;

    public List<PokemonDTO> gerarPokemonsParaEscolha() {
        List<PokemonDTO> pokemonsEscolha = new ArrayList<>();

        while (pokemonsEscolha.size() < 8) {
            int randomPokemonId = random.nextInt(898) + 1; // ID aleatório entre 1 e 898
            PokemonDTO response = pokeApiService.getPokemonNome(String.valueOf(randomPokemonId));

            boolean pokemonJaExiste = pokemonsEscolha.stream()
                    .anyMatch(pokemonEscolha -> pokemonEscolha.getNome().equalsIgnoreCase(response.getNome()));
            if (pokemonJaExiste) {
                continue;
            }

            PokemonDTO pokemonDTO = new PokemonDTO();
            pokemonDTO.setNome(response.getNome());
            pokemonDTO.setTipo(response.getTipo());
            pokemonDTO.setNivel(random.nextInt(50) + 1);
            pokemonDTO.setHp(random.nextInt(100) + 50);
            pokemonDTO.setAtaque(random.nextInt(50) + 10);
            pokemonDTO.setDefesa(random.nextInt(40) + 5);

            pokemonsEscolha.add(pokemonDTO);
        }

        return pokemonsEscolha;
    }


    public void cadastrarPokemonsEscolhidos(PokemonSelecionadoDTO dto) {
        if (dto.getPokemonEscolhidos().size() != 2) {
            throw new IllegalArgumentException("Você deve escolher exatamente 2 Pokémon.");
        }

        for (PokemonEscolhaDTO escolhido : dto.getPokemonEscolhidos()) {
            Long jogadorId = escolhido.getJogadorId();

            Jogador jogador = jogadorRepository.findById(jogadorId)
                    .orElseThrow(() -> new JogadorNotFoundException("Jogador com ID " + jogadorId + "não existe."));

            Long quantidadeExistente = pokemonRepository.countByJogadorId_JogadorId(jogadorId);
            if (quantidadeExistente >= 2) {
                throw new IllegalArgumentException("Jogador com ID " + jogadorId + " já possuí dois pokémon");
            }

            Pokemon pokemon = new Pokemon();
            BeanUtils.copyProperties(escolhido, pokemon);
            pokemon.setJogadorId(jogador);
            pokemonRepository.save(pokemon);
        }
    }
}
