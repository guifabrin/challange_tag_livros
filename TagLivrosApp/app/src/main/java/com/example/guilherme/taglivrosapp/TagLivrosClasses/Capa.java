package com.example.guilherme.taglivrosapp.TagLivrosClasses;

import android.graphics.Bitmap;

/**
 * Created by Guilherme on 11/09/2017.
 */

public class Capa {
    private String type;
    private String name;
    private String url;
    private boolean tentouBaixarImagem;
    private Bitmap imagem;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isTentouBaixarImagem() {
        return tentouBaixarImagem;
    }

    public void setTentouBaixarImagem(boolean tentouBaixarImagem) {
        this.tentouBaixarImagem = tentouBaixarImagem;
    }

    public Bitmap getImagem() {
        return imagem;
    }

    public void setImagem(Bitmap imagem) {
        this.imagem = imagem;
    }
}
