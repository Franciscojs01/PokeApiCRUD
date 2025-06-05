package com.example.pokemon.domain;

public enum EstadoBatalha {
    PRONTA_PARA_BATALHA(1, "Pronta para batalha"),
    FINALIZADA(2, "Batalha finalizada");

    private final int id;
    private final String descricao;

    EstadoBatalha(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public static EstadoBatalha fromId(Long id) {
        for (EstadoBatalha estado : values()) {
            if (estado.getId() == id) {
                return estado;
            }
        }
        throw new IllegalArgumentException("ID inválido para EstadoBatalha: " + id);
    }

    public static EstadoBatalha fromDescricao(String descricao) {
        for (EstadoBatalha estado : values()) {
            if (estado.getDescricao().equalsIgnoreCase(descricao)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Descrição inválida para EstadoBatalha: " + descricao);
    }

    public static String fromDescricaoById(Long id) {
        for (EstadoBatalha estado : values()) {
            if (estado.getId() == id) {
                return estado.getDescricao();
            }
        }
        throw new IllegalArgumentException("Descrição inválida para EstadoBatalha: " + id);
    }
}