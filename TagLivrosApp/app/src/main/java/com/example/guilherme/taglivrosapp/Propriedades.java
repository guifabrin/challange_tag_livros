package com.example.guilherme.taglivrosapp;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.ImageView;

import com.example.guilherme.taglivrosapp.TagLivrosClasses.Autor;
import com.example.guilherme.taglivrosapp.TagLivrosClasses.LivroTagLivros;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Guilherme on 11/09/2017.
 */

public class Propriedades {

    private static Propriedades instancia = null;
    private static final String[] meses = new String[]{"janeiro", "fevereiro", "março", "abril",
            "maio", "junho", "julho", "agosto", "setembro", "outubro", "novembro", "dezembro"} ;

    public static int getMes(String mes){
        for (int i=0; i<meses.length; i++){
            String m = meses[i];
            if (m.equals(mes.toLowerCase()))
                return i+1;
        }
        return 0;
    }

    public static void setStars(ImageView i, double d){
        if (d<0.5){
            i.setImageResource(R.mipmap.e00);
        } else if(d>=0.5 && d<1){
            i.setImageResource(R.mipmap.e05);
        } else if(d>=1 && d<1.5){
            i.setImageResource(R.mipmap.e10);
        } else if(d>=1.5 && d<2){
            i.setImageResource(R.mipmap.e15);
        } else if(d>=2 && d<2.5){
            i.setImageResource(R.mipmap.e20);
        } else if(d>=2.5 && d<3){
            i.setImageResource(R.mipmap.e25);
        } else if(d>=3 && d<3.5){
            i.setImageResource(R.mipmap.e30);
        } else if(d>=3.5 && d<4){
            i.setImageResource(R.mipmap.e35);
        } else if(d>=4 && d<4.5){
            i.setImageResource(R.mipmap.e40);
        } else if(d>=4.5 && d<5){
            i.setImageResource(R.mipmap.e45);
        } else {
            i.setImageResource(R.mipmap.e50);
        }
    }

    protected Propriedades(){}

    public static synchronized Propriedades getInstancia(){
        if(null == instancia){
            instancia = new Propriedades();
        }
        return instancia;
    }

    private Context contexto;
    private ArrayList<Autor> autores;
    private ArrayList<LivroTagLivros> livroTagLivros;
    private LivroTagLivros livroSelecionado;

    public ArrayList<LivroTagLivros> getLivroTagLivros() {
        LivroTagLivros[] lista = new LivroTagLivros[livroTagLivros.size()];
        lista = livroTagLivros.toArray(lista);
        for (int i = lista.length; i >= 1; i--) {
            for (int j = 1; j < i; j++) {
                int mesAnoJM1 = lista[j - 1].getEdition().getAnoMes();
                int mesAnoJ = lista[j].getEdition().getAnoMes();
                if (mesAnoJM1 <= mesAnoJ) {
                    LivroTagLivros aux = lista[j];
                    lista[j] = lista[j - 1];
                    lista[j - 1] = aux;
                }
            }
        }
        livroTagLivros = new ArrayList<>(Arrays.asList(lista));
        return livroTagLivros;
    }

    public void setLivroTagLivros(ArrayList<LivroTagLivros> livroTagLivros) {
        this.livroTagLivros = livroTagLivros;
    }

    public Context getContexto() {
        return contexto;
    }

    public void setContexto(Context contexto) {
        this.contexto = contexto;
    }

    private void initListaAutor(){
        if (autores==null)
            autores = new ArrayList<>();
    }

    public Autor getAutor(String nomeAutor){
        initListaAutor();
        for (Autor autor : autores){
            if (autor.getNome().toLowerCase().equals(nomeAutor.toLowerCase()))
                return autor;
        }
        Autor autor = new Autor(nomeAutor);
        autores.add(autor);
        return autor;
    }

    public LivroTagLivros getLivroSelecionado() {
        return livroSelecionado;
    }

    public void setLivroSelecionado(LivroTagLivros livroSelecionado) {
        this.livroSelecionado = livroSelecionado;
    }
}
