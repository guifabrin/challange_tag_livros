package com.example.guilherme.taglivrosapp.TagLivrosClasses;

import java.util.ArrayList;

/**
 * Created by Guilherme on 11/09/2017.
 */

public class Autor {
    private String nome;
    private ArrayList<LivroTagLivros> livroTagLivroses;

    public Autor(String nome) {
        this.nome = nome;
        this.livroTagLivroses = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<LivroTagLivros> getLivroTagLivroses() {
        return livroTagLivroses;
    }
}
