package com.example.pokemon.PokeApiResponse;

import java.util.List;

public class PokemonResponse {
    private String name;
    private int base_experience;
    private int weight;
    private List<TypeWrapper> types;

    public PokemonResponse() {
    }

    public PokemonResponse(String name, int base_experience, int weight, List<TypeWrapper> types) {
        this.name = name;
        this.base_experience = base_experience;
        this.weight = weight;
        this.types = types;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBase_experience() {
        return base_experience;
    }

    public void setBase_experience(int base_experience) {
        this.base_experience = base_experience;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<TypeWrapper> getTypes() {
        return types;
    }

    public void setTypes(List<TypeWrapper> types) {
        this.types = types;
    }

    public static class TypeWrapper {
        private Type type;


        public TypeWrapper() {}


        public TypeWrapper(Type type) {
            this.type = type;
        }


        public Type getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type;
        }
    }


    public static class Type {
        private String name;

        public Type() {

        }

        public Type(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Override
    public String toString() {
        return "PokemonResponse{" +
                "name='" + name + '\'' +
                ", base_experience=" + base_experience +
        ", weight=" + weight +
                ", types=" + types +
                '}';
    }

}
