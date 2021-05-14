package com.example.guilherme.taglivrosapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.guilherme.taglivrosapp.TagLivrosClasses.Capa;

import java.io.InputStream;

/**
 * Created by Guilherme on 11/09/2017.
 */

public class TarefaImagem extends AsyncTask<Capa, Void, Bitmap> {

    ImageView imageView;

    public TarefaImagem(ImageView imageView) {
        this.imageView = imageView;
    }

    protected Bitmap doInBackground(Capa... capas) {
        capas[0].setTentouBaixarImagem(true);
        String url = capas[0].getUrl();
        Bitmap imagem = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            imagem = BitmapFactory.decodeStream(in);
            capas[0].setImagem(imagem);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imagem;
    }

    protected void onPostExecute(Bitmap result) {
        imageView.setImageBitmap(result);
    }
}
