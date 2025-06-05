package com.example.pokemon.controller;

import org.example.Domain.Batalha;
import org.example.dto.BatalhaDTO;
import org.example.dto.BatalhaVencedorDTO;
import org.example.Service.BatalhaService;
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
    public ResponseEntity<String> cadastrarBatalha(@PathVariable Long id, @RequestBody Batalha batalha) {
        batalhaService.cadastrarBatalha(batalha, id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Batalha cadastrada com sucesso!");
    }

    @GetMapping("/iniciada/{id}")
    public ResponseEntity<BatalhaVencedorDTO> iniciarBatalha(@PathVariable Long id) {
        return ResponseEntity.ok().body(batalhaService.getBatalhaVencedor(id));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarBatalha(@PathVariable Long id) {
        batalhaService.deletarBatalha(id);Add commentMore actions
        return ResponseEntity.ok().body("Batalha deletada com sucesso!");
    }

}
