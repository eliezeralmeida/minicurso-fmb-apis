package com.minicurso.eliezer.app.feature;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CadastroPessoaActivity extends AppCompatActivity {
    private TextView nome;
    private TextView email;
    private Button botao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pessoa);

        nome = findViewById(R.id.nomeID);
        email = findViewById(R.id.emailID);
        botao = findViewById(R.id.botaoSalvarID);

        SetEventoBotao();
    }

    private void SetEventoBotao() {
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarPessoa();
            }
        });
    }

    private void salvarPessoa() {

        final PessoaApi pessoa = new PessoaApi();
        pessoa.nome = nome.getText().toString();
        pessoa.email = email.getText().toString();

        Thread run = new Thread(new Runnable() {
            @Override
            public void run() {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.43.40:8080")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                try {
                    PessoaServiceApi service = retrofit.create(PessoaServiceApi.class);
                    Response<Void> resposta = service.post(pessoa).execute();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            finish();
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
