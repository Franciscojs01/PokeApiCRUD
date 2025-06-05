package com.example.pokemon.controller;


import com.example.pokemon.Service.JogadorService;
import com.example.pokemon.dto.JogadorCadastroDTO;
import com.example.pokemon.dto.JogadorDTO;
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
        jogadorService.deletarUsuario(id);
        return ResponseEntity.ok().body("Jogador deletado com sucesso!");
    }

}
