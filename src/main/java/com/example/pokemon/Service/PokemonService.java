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

    public List<Pokemon> gerarPokemonsParaEscolha() {
        List<Pokemon> pokemonsEscolha = new ArrayList<>();

        while (pokemonsEscolha.size() < 8) {
            int randomPokemonId = random.nextInt(898) + 1;
            PokemonDTO response = pokeApiService.getPokemonNome(String.valueOf(randomPokemonId));

            boolean pokemonJaExiste = pokemonsEscolha.stream()
                    .anyMatch(pokemonEscolha -> pokemonEscolha.getNome().equalsIgnoreCase(response.getNome()));
            if (pokemonJaExiste) {
                continue;
            }

            Pokemon pokemon = new Pokemon();
            pokemon.setNome(response.getNome());
            pokemon.setTipo(response.getTipo());
            pokemon.setNivel(random.nextInt(50) + 1);
            pokemon.setHp(random.nextInt(100) + 50);
            pokemon.setAtaque(random.nextInt(50) + 10);
            pokemon.setDefesa(random.nextInt(40) + 5);


            pokemonsEscolha.add(pokemon);
            pokemonRepository.save(pokemon);
        }

        return pokemonsEscolha;
    }


    public void cadastrarPokemonsEscolhidos(PokemonSelecionadoDTO dto) {
        if (dto.getPokemonEscolhidos().size() != 2) {
            throw new IllegalArgumentException("Você deve escolher exatamente 2 Pokémon.");
        }

        for (PokemonEscolhaDTO escolhido : dto.getPokemonEscolhidos()) {
            Long jogadorId = escolhido.getJogadorId();
            Long pokemonId = escolhido.getPokemonId();
            Jogador jogador = jogadorRepository.findById(jogadorId)
                    .orElseThrow(() -> new JogadorNotFoundException("Jogador com ID " + jogadorId + "não existe."));

            Long quantidadeExistente = pokemonRepository.countByJogadorId_JogadorId(jogadorId);
            if (quantidadeExistente >= 2) {
                throw new IllegalArgumentException("Jogador com ID " + jogadorId + " já possuí dois pokémon");
            }

            Pokemon pokemon = pokemonRepository.findById(pokemonId)
                    .orElseThrow(() -> new IllegalArgumentException("Pokemon com esse " + pokemonId + " Não foi encontrado."));

            if (pokemon.getJogadorId() != null) {
                throw new IllegalArgumentException("O pokemon com esse " + pokemonId + " Já foi escolhido.");
            }

//            Pokemon pokemon = new Pokemon();
//            BeanUtils.copyProperties(escolhido, pokemon);
            pokemon.setJogadorId(jogador);
            pokemonRepository.save(pokemon);
        }
    }
}
