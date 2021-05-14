package com.example.guilherme.taglivrosapp;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.guilherme.taglivrosapp.TagLivrosClasses.LivroTagLivros;

import java.util.concurrent.Callable;

public class AtividadePrimaria extends AppCompatActivity {

    private SwipeRefreshLayout swipeContainer;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("TagLivros - Últimos Avaliados");
        Propriedades.getInstancia().setContexto(AtividadePrimaria.this);

        setContentView(R.layout.activity_main);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                atualizaListView();
                swipeContainer.setRefreshing(false);
            }
        });

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setClickable(true);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                LivroTagLivros livroTagLivros = Propriedades.getInstancia().getLivroTagLivros().get(position);
                Propriedades.getInstancia().setLivroSelecionado(livroTagLivros);
                Intent secondActivity = new Intent(Propriedades.getInstancia().getContexto(), AtividadeItemDescricao.class);
                startActivity(secondActivity);
            }
        });

        if (!isOnline()){
            new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Sem internet")
                .setMessage("Não foi possível conectar a internet, posso tentar novamente?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent mStartActivity = new Intent(Propriedades.getInstancia().getContexto(), AtividadePrimaria.class);
                        int mPendingIntentId = 123456;
                        PendingIntent mPendingIntent = PendingIntent.getActivity(Propriedades.getInstancia().getContexto(), mPendingIntentId,    mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                        AlarmManager mgr = (AlarmManager)Propriedades.getInstancia().getContexto().getSystemService(Context.ALARM_SERVICE);
                        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                        System.exit(0);
                    }
                })
                .show();
        } else {
            Callable callable = new Callable() {
                @Override
                public Object call() throws Exception {
                    atualizaListView();
                    return null;
                }
            };
            TarefaTagLivrosJSON tarefa = new TarefaTagLivrosJSON("http://guifabrin.000webhostapp.com/tag_livros_example.json", callable);
            tarefa.execute();
        }

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.btn_dialog)
                .setTitle("Fechar aplicativo")
                .setMessage("Você tem certeza que deseja sair?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("Não", null)
                .show();
    }

    private void atualizaListView(){
        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(new Adaptador(Propriedades.getInstancia().getLivroTagLivros()));
    }

    public boolean isOnline() {
        try {
            ConnectivityManager cm =(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return netInfo != null && netInfo.isConnectedOrConnecting();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
}
