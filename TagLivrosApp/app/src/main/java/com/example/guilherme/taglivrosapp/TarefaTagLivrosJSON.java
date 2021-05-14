package com.example.guilherme.taglivrosapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.example.guilherme.taglivrosapp.TagLivrosClasses.Capa;
import com.example.guilherme.taglivrosapp.TagLivrosClasses.Edicao;
import com.example.guilherme.taglivrosapp.TagLivrosClasses.LivroTagLivros;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 * Created by Guilherme on 11/09/2017.
 */

public class TarefaTagLivrosJSON extends AsyncTask<Void, Void, String> {

    private String url;
    private Callable callable;
    private ProgressDialog dialogoCarregamento;

    public TarefaTagLivrosJSON(String url, Callable callable) {
        this.url = url;
        this.callable = callable;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialogoCarregamento = new ProgressDialog(Propriedades.getInstancia().getContexto());
        dialogoCarregamento.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialogoCarregamento.setMessage("Carregando...");
        dialogoCarregamento.setIndeterminate(true);
        dialogoCarregamento.setCanceledOnTouchOutside(false);
        dialogoCarregamento.show();
    }

    @Override
    protected String doInBackground(Void... params) {
        HttpURLConnection c = null;
        try {
            URL u = new URL(url);
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
        try {
            JSONObject jsonObject = new JSONObject(jsonInString);
            JSONArray jsonResults = jsonObject.getJSONArray("results");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            ArrayList<LivroTagLivros> lista = new ArrayList<>();
            for (int i=0; i < jsonResults.length(); i++) {
                JSONObject livro = jsonResults.getJSONObject(i);
                LivroTagLivros livroTagLivros = new LivroTagLivros();
                livroTagLivros.setObjectId(livro.getString("objectId"));
                livroTagLivros.setName(livro.getString("name"));
                livroTagLivros.setIsbn(livro.getString("isbn"));
                try {
                    livroTagLivros.setCreatedAt(sdf.parse(livro.getString("createdAt")));
                } catch (Exception ex){
                    ex.printStackTrace();
                }
                try {
                    livroTagLivros.setUpdatedAt(sdf.parse(livro.getString("updatedAt")));
                } catch (Exception ex){
                    ex.printStackTrace();
                }
                livroTagLivros.setAuthor(Propriedades.getInstancia().getAutor(livro.getString("author")));
                livroTagLivros.setPages(livro.getInt("pages"));
                livroTagLivros.setCurator(Propriedades.getInstancia().getAutor(livro.getString("curator")));
                String[] edicao = livro.getString("edition").split(" de ");
                livroTagLivros.setEdition(new Edicao(edicao[0], Propriedades.getMes(edicao[0]),Integer.parseInt(edicao[1])));
                livroTagLivros.setBlocked(livro.getBoolean("blocked"));
                livroTagLivros.setNumRatings(livro.getInt("numRatings"));
                livroTagLivros.setTotalRatings(livro.getInt("totalRatings"));
                Capa capa = new Capa();
                JSONObject coverJSON = livro.getJSONObject("cover");
                capa.setType(coverJSON.getString("__type"));
                capa.setUrl(coverJSON.getString("url"));
                capa.setName(coverJSON.getString("name"));
                livroTagLivros.setCover(capa);
                lista.add(livroTagLivros);
            }
            TarefaGoodReadJSON tgrj = new TarefaGoodReadJSON(lista, dialogoCarregamento, callable);
            tgrj.execute();
        } catch (Exception ex){
            ex.printStackTrace();
            dialogoCarregamento.hide();
        }
    }
}
