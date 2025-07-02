package com.example.pokemon.controller;

import com.example.pokemon.Service.BatalhaService;
import com.example.pokemon.dto.BatalhaCadastroDTO;
import com.example.pokemon.dto.BatalhaDTO;
import com.example.pokemon.dto.BatalhaVencedorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/batalha")

public class BatalhaController {
    @Autowired
    BatalhaService batalhaService;

    @GetMapping("/teste/{id}")
    public ResponseEntity<BatalhaDTO> testarBatalha(@PathVariable Long id) {
        return ResponseEntity.ok().body(batalhaService.getBatalha(id));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarBatalha(@RequestBody BatalhaCadastroDTO batalha) {
        batalhaService.cadastrarBatalha(batalha);
        return ResponseEntity.status(HttpStatus.CREATED).body("Batalha cadastrada com sucesso!");
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<BatalhaDTO> editarBatalha(@PathVariable Long id, @RequestBody BatalhaCadastroDTO batalha) {
        BatalhaDTO batalhaAtualizada = batalhaService.editarBatalha(id, batalha.getEstado());
        return ResponseEntity.ok().body(batalhaAtualizada);
    }

    @GetMapping("/vencedor/{id}")
    public ResponseEntity<BatalhaVencedorDTO> iniciarBatalha(@PathVariable Long id) {
        return ResponseEntity.ok().body(batalhaService.getBatalhaVencedor(id));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarBatalha(@PathVariable Long id) {
        batalhaService.deletarBatalha(id);
        return ResponseEntity.ok().body("Batalha deletada com sucesso!");
    }

}
