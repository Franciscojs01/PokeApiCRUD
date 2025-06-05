package com.example.pokemon.Service;

import com.example.pokemon.domain.Batalha;
import com.example.pokemon.domain.EstadoBatalha;
import com.example.pokemon.domain.Jogador;
import com.example.pokemon.domain.Pokemon;
import com.example.pokemon.dto.*;
import com.example.pokemon.exceptions.BatalhaDuplicadoException;
import com.example.pokemon.exceptions.BatalhaNotFoundException;
import com.example.pokemon.exceptions.JogadorNotFoundException;
import com.example.pokemon.repository.BatalhaRepository;
import com.example.pokemon.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatalhaService {
    @Autowired
    BatalhaRepository batalhaRepository;

    @Autowired
    JogadorRepository jogadorRepository;

    public BatalhaDTO getBatalha(Long id) {
        Batalha batalha = batalhaRepository.findById(id).orElseThrow(() -> new BatalhaNotFoundException("Batalha não existe."));

        JogadorBatalhaDTO jogadorBatalhaDTO = montarJogadorDTO(batalha.getJogador1());
        JogadorBatalhaDTO jogadorBatalhaDTO2 = montarJogadorDTO(batalha.getJogador2());

        return new BatalhaDTO(
                jogadorBatalhaDTO,
                jogadorBatalhaDTO2,
                LocalDate.now(),
                batalha.getEstado()
        );
    }

    public BatalhaDTO cadastrarBatalha(BatalhaCadastroDTO batalha) {
        validarBatalha(batalha.getJogador1(), batalha.getJogador2());
        Jogador jogador = jogadorRepository.findById(batalha.getJogador1()).orElseThrow(() -> new JogadorNotFoundException("Jogador 1,  não encontrado."));
        Jogador jogador2 = jogadorRepository.findById(batalha.getJogador2()).orElseThrow(() -> new JogadorNotFoundException("Jogador 2,  não encontrado."));

        Batalha novaBatalha = new Batalha(
                jogador,
                jogador2, EstadoBatalha.fromId(batalha.getEstado()),
                batalha.getDataCadastro()
        );

        JogadorBatalhaDTO jogador1DTO = montarJogadorDTO(jogador);
        JogadorBatalhaDTO jogador2DTO = montarJogadorDTO(jogador2);

        return new BatalhaDTO(jogador1DTO, jogador2DTO, novaBatalha.getDataCriacao(), novaBatalha.getEstado());
    }

    public BatalhaDTO editarBatalha(Long id, Long novoEstadoId) {
        Batalha batalha = batalhaRepository.findById(id)
                .orElseThrow(() -> new BatalhaNotFoundException("Batalha com id: " + id + "não foi encontrado"));

        EstadoBatalha novoEstado = EstadoBatalha.fromId(novoEstadoId);

        if (batalha.getEstado().equals(novoEstado)) {
            throw new IllegalArgumentException("A Batalha já está no estado " + novoEstado + ". Atualização não permitida.");
        }

        batalha.setEstado(novoEstado);

        batalhaRepository.save(batalha);

        JogadorBatalhaDTO jogador1DTO = montarJogadorDTO(batalha.getJogador1());
        JogadorBatalhaDTO jogador2DTO = montarJogadorDTO(batalha.getJogador2());

        return new BatalhaDTO(jogador1DTO, jogador2DTO, batalha.getDataCriacao(), batalha.getEstado());
    }

    public BatalhaVencedorDTO getBatalhaVencedor(Long id) {
        Batalha batalha = batalhaRepository.findById(id)
                .orElseThrow(() -> new BatalhaNotFoundException("Batalha não encontrada."));

        List<Pokemon> pokemonsJogador1 = batalha.getJogador1().getPokemon();
        List<Pokemon> pokemonsJogador2 = batalha.getJogador2().getPokemon();

        if (pokemonsJogador1.size() < 2 || pokemonsJogador2.size() < 2) {
            throw new IllegalArgumentException("Ambos os jogadores devem ter pelo menos 2 Pokémon.");
        }

        int derrotasJogador1 = 0;
        int derrotasJogador2 = 0;

        for (int contador = 0; contador < 2; contador++) {
            Pokemon pokemon1 = pokemonsJogador1.get(contador);
            Pokemon pokemon2 = pokemonsJogador2.get(contador);

            int vencedor = simularLutar(pokemon1, pokemon2);

            if (vencedor == 1) {
                derrotasJogador2++;
            } else if (vencedor == 2) {
                derrotasJogador1++;
            }
        }

        String vencedor;
        if (derrotasJogador1 == 2) {
            vencedor = batalha.getJogador2().getNome();
        } else if (derrotasJogador2 == 2) {
            vencedor = batalha.getJogador1().getNome();
        } else {
            vencedor = "Empate";
        }

        return new BatalhaVencedorDTO(vencedor);
    }


    public void deletarBatalha(Long id) {
        if (batalhaRepository.existsById(id)) {
            batalhaRepository.deleteById(id);
        } else {
            throw new BatalhaNotFoundException("Batalha não existe.");
        }

    }

    public JogadorBatalhaDTO montarJogadorDTO(Jogador jogador) {
        List<PokemonDTO> pokemons = jogador.getPokemon().
                stream().
                map(pokemon ->
                        new PokemonDTO(pokemon.getNome(), pokemon.getTipo(), pokemon.getNivel(), pokemon.getHp(), pokemon.getAtaque(), pokemon.getDefesa())).
                collect(Collectors.toList());

        return new JogadorBatalhaDTO(pokemons, jogador.getNome());
    }

    public int simularLutar(Pokemon pokemon1, Pokemon pokemon2) {
        int hp1 = pokemon1.getHp();
        int hp2 = pokemon2.getHp();

        int dano1 = Math.max(1, pokemon1.getAtaque() - pokemon2.getDefesa());
        int dano2 = Math.max(1, pokemon2.getAtaque() - pokemon1.getDefesa());

        while (hp1 > 0 && hp2 > 0) {
            hp2 -= dano1;
            if (hp2 <= 0) break;

            hp1 -= dano2;
        }

        if (hp1 > 0) return 1;
        else if (hp2 > 0) return 2;
        else return 0;
    }

    public void validarBatalha(Long idJogador1, Long idJogador2) {
        Long numeroBatalha = batalhaRepository.countByJogador1Id(idJogador1);
        Long numeroBatalha2 = batalhaRepository.countByJogador2Id(idJogador2);

        if (numeroBatalha > 0 || numeroBatalha2 > 0) {
            throw new BatalhaDuplicadoException("Batalha já existente.");
        }
    }

}
