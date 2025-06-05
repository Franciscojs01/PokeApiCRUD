package com.example.pokemon.Service;

import com.example.pokemon.PokeApiResponse.PokemonResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PokeApiService {
    private final String POKEAPI_URL = "https://pokeapi.co/api/v2/pokemon/";

    public PokemonDTO getPokemonNome(String nome) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            PokemonResponse response = restTemplate.getForObject(POKEAPI_URL + nome, PokemonResponse.class);

            if (response == null || response.getName() == null) {
                throw new RuntimeException("Pokémon não encontrado!");
            }

            String tipo = response.getTypes().isEmpty() ? "desconhecido" : response.getTypes().get(0).getType().getName();

            return new PokemonDTO(
                    response.getName(),
                    tipo,
                    1,
                    50 + (int) (Math.random() * 100),
                    response.getBase_experience(),
                    response.getWeight()
            );
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar Pokémon: " + e.getMessage());
        }

    }
}
