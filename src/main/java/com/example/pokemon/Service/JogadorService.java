package com.example.pokemon.Service;

import com.example.pokemon.domain.Jogador;
import com.example.pokemon.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class JogadorService implements UserDetailsService {
    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public JogadorDTO cadastrarUsuario(JogadorCadastroDTO jogadorCadastroDto)  {
        String email = jogadorCadastroDto.getEmail();

        jogadorRepository.findByEmail(email)
                .ifPresent(jogadorExistente -> {
                    throw new JogadorDuplicadoException("Jogador com esse email: " + email + " já existe.");
                });
        String senhaCriptografada = passwordEncoder.encode(jogadorCadastroDto.getSenha());
        Jogador novoJogador = new Jogador(jogadorCadastroDto.getNome(), jogadorCadastroDto.getEmail(), senhaCriptografada, LocalDate.now());
        jogadorRepository.save(novoJogador);
        return  new JogadorDTO(novoJogador.getNome(), novoJogador.getEmail());
    }


    public void deletarUsuario(Long id) {
        if (jogadorRepository.existsById(id)) {
            jogadorRepository.deleteById(id);
        } else {
            throw new JogadorNotFoundException("Jogador não foi encontrado.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {Add commentMore actions
        return jogadorRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!."));
    }
}
