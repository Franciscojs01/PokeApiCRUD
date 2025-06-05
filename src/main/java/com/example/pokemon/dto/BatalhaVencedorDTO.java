package com.example.pokemon.dto;

public class BatalhaVencedorDTO {
    private String nomeVencedor;

    public BatalhaVencedorDTO(String vencedor) {
        this.nomeVencedor = vencedor;
    }

    public String getNomeVencedor() {
        return nomeVencedor;
    }

    public void setNomeVencedor(String nomeVencedor) {
        this.nomeVencedor = nomeVencedor;
    }
}

