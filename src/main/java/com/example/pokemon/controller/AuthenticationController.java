package com.example.pokemon.controller;

import com.example.pokemon.domain.Jogador;
import com.example.pokemon.dto.AuthenticationDTO;
import com.example.pokemon.dto.LoginDTO;

import com.example.pokemon.infra.config.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginDTO> login(@RequestBody AuthenticationDTO data) {
        Authentication user = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        Authentication authenticate = authenticationManager.authenticate(user);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        Jogador jogador = (Jogador) authenticate.getPrincipal();

        var token = tokenService.generateToken(jogador);

        return ResponseEntity.ok(new LoginDTO(jogador.getJogadorId(), jogador.getEmail(), token));
    }
}
