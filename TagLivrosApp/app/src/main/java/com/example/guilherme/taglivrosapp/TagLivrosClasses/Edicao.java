package com.example.guilherme.taglivrosapp.TagLivrosClasses;

/**
 * Created by Guilherme on 12/09/2017.
 */

public class Edicao {
    private int mes;
    private String mesString;
    private int ano;

    public Edicao(String mesString, int mes, int ano) {
        this.mes = mes;
        this.mesString = mesString;
        this.ano = ano;
    }

    public int getMes() {
        return mes;
    }

    public String getMesString() {
        return mesString;
    }

    public int getAno() {
        return ano;
    }

    public int getAnoMes(){
        return Integer.parseInt(getAno()+""+getMes());
    }
}
