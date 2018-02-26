package com.minicurso.eliezer.app.feature;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.refreshID) {
            atualizaDadosView();
        }

        if (item.getItemId() == R.id.novoID) {
            startActivity(new Intent(MainActivity.this, CadastroPessoaActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        atualizaDadosView();
    }

    private void atualizaDadosView() {
        final ListView listView = findViewById(R.id.listViewPessoaID);

        Thread run = new Thread(new Runnable() {
            @Override
            public void run() {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.43.40:8080")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                try {
                    PessoaServiceApi service = retrofit.create(PessoaServiceApi.class);
                    Response<List<PessoaApi>> response = service.listAll().execute();
                    final List<PessoaApi> pessoas = response.body();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ArrayAdapter adapter = new ListViewPessoaAdapter(MainActivity.this, pessoas);
                            listView.setAdapter(adapter);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        run.start();
    }
}
