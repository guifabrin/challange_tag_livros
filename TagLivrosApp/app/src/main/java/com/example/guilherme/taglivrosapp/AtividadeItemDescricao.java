package com.example.guilherme.taglivrosapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guilherme.taglivrosapp.TagLivrosClasses.LivroTagLivros;

import java.text.DecimalFormat;

public class AtividadeItemDescricao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("TagLivros - Detalhamento");
        setContentView(R.layout.activity_item);

        LivroTagLivros livroTagLivros = Propriedades.getInstancia().getLivroSelecionado();

        ImageView imagemViewCapa =(ImageView)findViewById(R.id.descricao_capa);
        if (livroTagLivros.getCover().isTentouBaixarImagem()){
            if (livroTagLivros.getCover().getImagem()==null)
                imagemViewCapa.setImageResource(R.mipmap.no_image_avaliable);
            else {
                imagemViewCapa.setImageBitmap(livroTagLivros.getCover().getImagem());
            }
        } else {
            TarefaImagem tarefa = new TarefaImagem(imagemViewCapa);
            tarefa.execute(livroTagLivros.getCover());
        }
        ImageView imagemViewTagLivros =(ImageView)findViewById(R.id.descricao_tag_livros);
        double votacaoTag = livroTagLivros.getTotalRatings()*1.0/livroTagLivros.getNumRatings()*1.0;
        Propriedades.setStars(imagemViewTagLivros, votacaoTag);

        TextView textViewTitulo = (TextView)findViewById(R.id.descricao_titulo);
        textViewTitulo.setText(livroTagLivros.getName());
        TextView textViewAutor = (TextView)findViewById(R.id.descricao_autor);
        textViewAutor.setText(livroTagLivros.getAuthor().getNome());
        TextView textViewMesAno = (TextView)findViewById(R.id.descricao_mes_ano);
        textViewMesAno.setText(livroTagLivros.getEdition().getMesString()+" de "+livroTagLivros.getEdition().getAno());
        TextView textViewCurador = (TextView)findViewById(R.id.descricao_curador);
        textViewCurador.setText(livroTagLivros.getCurator().getNome());
        TextView textViewPaginas = (TextView)findViewById(R.id.descricao_paginas);
        textViewPaginas.setText(livroTagLivros.getPages().toString());
        TextView textViewTagLivrosValor = (TextView)findViewById(R.id.descricao_tag_livros_valor);
        textViewTagLivrosValor.setText(new DecimalFormat("#.00").format(votacaoTag) + "/5");
        TextView textViewGoodReadValor = (TextView)findViewById(R.id.descricao_good_read_valor);

        ImageView imagemViewGoodRead =(ImageView)findViewById(R.id.descricao_good_read);
        if (livroTagLivros.getLivroGoodRead()!=null) {
            Double votacaoGoodReading = livroTagLivros.getLivroGoodRead().getAverageRating();
            if (votacaoGoodReading != null)
                textViewGoodReadValor.setText(new DecimalFormat("#.00").format(votacaoGoodReading) + "/5");
            Propriedades.setStars(imagemViewGoodRead, livroTagLivros.getLivroGoodRead().getAverageRating()  );
        } else {
            textViewGoodReadValor.setText("NC/5");
            Propriedades.setStars(imagemViewGoodRead, 0);
        }
    }
}
