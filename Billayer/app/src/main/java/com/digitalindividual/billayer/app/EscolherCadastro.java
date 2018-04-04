package com.digitalindividual.billayer.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.digitalindividual.billayer.R;

public class EscolherCadastro extends AppCompatActivity {

    Button btnLancamento;
    Button btnDespesa;
    Button btnCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolher_cadastro);

        btnLancamento = (Button) findViewById(R.id.btn_cadastrar_receita);
        btnDespesa = (Button) findViewById(R.id.btn_cadastrar_despesa);
        btnCategoria = (Button) findViewById(R.id.btn_escolher_categoria);

        btnLancamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Cadastro.class);

                intent.putExtra("tipo", "receita");

                startActivity(intent);

            }
        });

        btnDespesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Cadastro.class);

                intent.putExtra("tipo", "despesa");

                startActivity(intent);

            }
        });

        btnCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =  new Intent(getApplicationContext(), CadastroCategoria.class);

                startActivity(intent);

            }
        });

    }
}
