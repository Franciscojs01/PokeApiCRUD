package com.example.pokemon.controller;

import org.example.dto.JogadorCadastroDTO;
import org.example.dto.JogadorDTO;
import org.example.Service.JogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jogador")

public class JogadorController {

    @Autowired
    JogadorService jogadorService;

    @PostMapping("/cadastro")
    public ResponseEntity<JogadorDTO> cadastrarJogador(@RequestBody JogadorCadastroDTO jogadorCadastroDto) {
        JogadorDTO jogadorDTO = jogadorService.cadastrarUsuario(jogadorCadastroDto);
        return ResponseEntity.ok().body(jogadorDTO);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarJogador(@PathVariable long id) {
        jogadorService.deletarUsuario(id);Add commentMore actions
        return ResponseEntity.ok().body("Jogador deletado com sucesso!");
    }

}
