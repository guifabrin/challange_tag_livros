package com.example.guilherme.taglivrosapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.example.guilherme.taglivrosapp.GoodReadClasses.LivroGoodRead;
import com.example.guilherme.taglivrosapp.TagLivrosClasses.LivroTagLivros;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 * Created by Guilherme on 11/09/2017.
 */

public class TarefaGoodReadJSON extends AsyncTask<Void, Void, String> {

    private Callable callable;
    private ArrayList<LivroTagLivros> livros;
    private ProgressDialog dialogoCarregamento;

    public TarefaGoodReadJSON(ArrayList<LivroTagLivros> livros, ProgressDialog dialogoCarregamento, Callable callable) {
        this.livros = livros;
        this.callable = callable;
        this.dialogoCarregamento = dialogoCarregamento;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params) {
        String isbns = "";
        for (LivroTagLivros livroTagLivros : livros){
            if (isbns!="")
                isbns+=",";
            isbns+= livroTagLivros.getIsbn();
        }
        HttpURLConnection c = null;
        try {
            URL u = new URL("https://www.goodreads.com/book/review_counts.json?isbns="+isbns+"&key=3TDdTdePnbhBPG5u3BwbyA");
            Log.d("u", "https://www.goodreads.com/book/review_counts.json?isbns="+isbns+"&key=3TDdTdePnbhBPG5u3BwbyA");
            c = (HttpURLConnection) u.openConnection();
            c.connect();
            int status = c.getResponseCode();
            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line+"\n");
                    }
                    br.close();
                    return sb.toString();
            }

        } catch (Exception ex) {
            return ex.toString();
        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception ex) {
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String jsonInString) {
        super.onPostExecute(jsonInString);
        try {JSONObject jsonObject = new JSONObject(jsonInString);
            JSONArray jsonResults = jsonObject.getJSONArray("books");
            for (int i=0; i < jsonResults.length(); i++) {
                JSONObject livro = jsonResults.getJSONObject(i);
                LivroGoodRead livroGoodRead = new LivroGoodRead();
                livroGoodRead.setAverageRating(livro.getDouble("average_rating"));
                livroGoodRead.setId(livro.getInt("id"));
                livroGoodRead.setIsbn(livro.getString("isbn"));
                livroGoodRead.setIsbn13(livro.getString("isbn13"));
                livroGoodRead.setRatingsCount(livro.getInt("ratings_count"));
                livroGoodRead.setTextReviewsCount(livro.getInt("text_reviews_count"));
                livroGoodRead.setReviewsCount(livro.getInt("reviews_count"));
                livroGoodRead.setWorkRatingsCount(livro.getInt("work_ratings_count"));
                livroGoodRead.setWorkReviewsCount(livro.getInt("work_reviews_count"));
                livroGoodRead.setWorkTextReviewsCount(livro.getInt("work_text_reviews_count"));
                for (LivroTagLivros livroTagLivros: livros){
                    if (livroTagLivros.getIsbn().equals(livroGoodRead.getIsbn13())){
                        livroTagLivros.setLivroGoodRead(livroGoodRead);
                    }
                }
            }
            Propriedades.getInstancia().setLivroTagLivros(livros);
            callable.call();
            dialogoCarregamento.hide();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}