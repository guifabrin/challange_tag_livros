package com.example.guilherme.taglivrosapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guilherme.taglivrosapp.TagLivrosClasses.LivroTagLivros;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Guilherme on 11/09/2017.
 */

public class Adaptador extends BaseAdapter {

    private ArrayList<LivroTagLivros> itens;

    public Adaptador(ArrayList<LivroTagLivros> itens) {
        this.itens = itens;
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int posicao) {
        return itens.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return posicao;
    }

    @Override
    public View getView(int posicao, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) Propriedades.getInstancia().getContexto().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView =  inflater.inflate(R.layout.list_item, null);
        }

        TextView titulo = (TextView)convertView.findViewById(R.id.item_titulo);
        TextView autor =(TextView)convertView.findViewById(R.id.item_autor);
        ImageView imagemView =(ImageView)convertView.findViewById(R.id.item_capa);
        TextView votacaoTagValor =(TextView)convertView.findViewById(R.id.votacaoTagValor);
        ImageView imagemViewVTag =(ImageView)convertView.findViewById(R.id.votacaoTag);
        TextView votacaoGoodReadingValor =(TextView)convertView.findViewById(R.id.votacaoGoodReadingValor);
        ImageView imagemViewVGoodReading =(ImageView)convertView.findViewById(R.id.votacaoGoodReading);

        LivroTagLivros item = itens.get(posicao);
        titulo.setText(item.getName());
        autor.setText(item.getAuthor().getNome());
        if (item.getCover().isTentouBaixarImagem()){
            if (item.getCover().getImagem()==null)
                imagemView.setImageResource(R.mipmap.no_image_avaliable);
            else {
                imagemView.setImageBitmap(item.getCover().getImagem());
            }
        } else {
            TarefaImagem tarefa = new TarefaImagem(imagemView);
            tarefa.execute(item.getCover());
        }
        double votacaoTag = item.getTotalRatings()*1.0/item.getNumRatings()*1.0;
        votacaoTagValor.setText(new DecimalFormat("#.00").format(votacaoTag)+"/5");
        Propriedades.setStars(imagemViewVTag, votacaoTag);
        if (item.getLivroGoodRead()!=null) {
            Double votacaoGoodReading = item.getLivroGoodRead().getAverageRating();
            if (votacaoGoodReading != null) {
                votacaoGoodReadingValor.setText(new DecimalFormat("#.00").format(votacaoGoodReading) + "/5");
            }
            Propriedades.setStars(imagemViewVGoodReading, item.getLivroGoodRead().getAverageRating());
        } else {
            votacaoGoodReadingValor.setText("NC/5");
            Propriedades.setStars(imagemViewVGoodReading, 0);
        }
        return convertView;
    }


}
