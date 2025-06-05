package com.example.pokemon.controller;

import org.example.Domain.Jogador;
import org.example.dto.AuthenticationDTO;
import org.example.dto.LoginDTO;
import org.example.infra.Config.TokenService;
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
        SecurityContextHolder.getContext().setAuthentication(authenticate);Add commentMore actions
        Jogador jogador = (Jogador) authenticate.getPrincipal();

        var token = tokenService.generateToken(jogador);

        return ResponseEntity.ok(new LoginDTO(jogador.getJogadorId(), jogador.getEmail(), token));
    }
}
