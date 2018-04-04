package com.digitalindividual.billayer.app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.digitalindividual.billayer.R;
import com.digitalindividual.billayer.dao.CategoriaDAO;
import com.digitalindividual.billayer.dao.LancamentoDAO;
import com.digitalindividual.billayer.models.Categoria;
import com.digitalindividual.billayer.models.Lancamento;
import com.digitalindividual.billayer.util.ControleData;

import java.text.NumberFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView txtSaldo;

    ImageButton btnReceitas;
    ImageButton btnDispesas;
    ImageButton btnCategoria;

    LancamentoDAO lancamentoDAO = LancamentoDAO.getInstance();

    Lancamento lancamento = new Lancamento();

    ControleData controleData = new ControleData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CategoriaDAO categoriaDAO = new CategoriaDAO();

        txtSaldo = (TextView) findViewById(R.id.txt_saldo);

        ArrayList<Lancamento> listaLancamentos = new ArrayList<>();

        double despesaAtual = 0;

        double receitaAtual = 0;

        listaLancamentos = lancamentoDAO.selecionarTodos(getApplicationContext());

        for(int i = 0; i < listaLancamentos.size(); i++){

            Date dataLancamento = listaLancamentos.get(i).getData();

            if(controleData.dataAtual(dataLancamento)){

                if(listaLancamentos.get(i).getTipo().equals("receita")){

                    receitaAtual = receitaAtual + listaLancamentos.get(i).getValor();

                } else if(listaLancamentos.get(i).getTipo().equals("despesa")){

                   despesaAtual = despesaAtual + listaLancamentos.get(i).getValor();

                }

            }

        }

        double saldoAtual = receitaAtual - despesaAtual;

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "br"));

        txtSaldo.setText(numberFormat.format(saldoAtual));

        ArrayList<Categoria> listaCategoria = new ArrayList<>();

        listaCategoria = categoriaDAO.selecionarTodas(getApplicationContext());

        if(listaCategoria.size() == 0){

            //Toast.makeText(getApplicationContext(), "Lista Categoria Padrão Inserida!", Toast.LENGTH_SHORT).show();

            listaCategoria = listaCategoriaPadrao();

            for(int i = 0; listaCategoria.size() > i; i++){

                categoriaDAO.inserir(getApplicationContext(), listaCategoria.get(i));

            }

        }

        btnReceitas = (ImageButton) findViewById(R.id.btn_receitas);

        btnReceitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), LancamentosActivity.class);

                intent.putExtra("Tipo", "receita");

                startActivity(intent);

            }
        });

        btnDispesas = (ImageButton) findViewById(R.id.btn_dispesas);

        btnDispesas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), LancamentosActivity.class);

                intent.putExtra("Tipo", "despesa");

                startActivity(intent);

            }
        });

        btnCategoria = (ImageButton) findViewById(R.id.btn_categoria);

        btnCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), CategoriasActivity.class);

                startActivity(intent);

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), EscolherCadastro.class);

                startActivity(intent);

            }
        });
    }

    private ArrayList<Categoria> listaCategoriaPadrao(){

        ArrayList<Categoria> listaCategoria = new ArrayList<>();

        Categoria categoria;

        categoria =  new Categoria();

        categoria.setImagem(BitmapFactory.decodeResource(getResources(), R.drawable.salario));
        categoria.setNome("Salário");

        listaCategoria.add(categoria);

        categoria =  new Categoria();

        categoria.setImagem(BitmapFactory.decodeResource(getResources(), R.drawable.alimentacao));
        categoria.setNome("Alimentação");

        listaCategoria.add(categoria);

        categoria =  new Categoria();

        categoria.setImagem(BitmapFactory.decodeResource(getResources(), R.drawable.saude));
        categoria.setNome("Saúde");

        listaCategoria.add(categoria);

        categoria =  new Categoria();

        categoria.setImagem(BitmapFactory.decodeResource(getResources(), R.drawable.transporte));
        categoria.setNome("Transporte");

        listaCategoria.add(categoria);

        categoria =  new Categoria();

        categoria.setImagem(BitmapFactory.decodeResource(getResources(), R.drawable.lazer));
        categoria.setNome("Lazer");

        listaCategoria.add(categoria);

        categoria =  new Categoria();

        categoria.setImagem(BitmapFactory.decodeResource(getResources(), R.drawable.home));
        categoria.setNome("Moradia");

        listaCategoria.add(categoria);

        categoria =  new Categoria();

        categoria.setImagem(BitmapFactory.decodeResource(getResources(), R.drawable.negocios));
        categoria.setNome("Negócios");

        listaCategoria.add(categoria);

        categoria =  new Categoria();

        categoria.setImagem(BitmapFactory.decodeResource(getResources(), R.drawable.educacao));
        categoria.setNome("Educação");

        listaCategoria.add(categoria);

        return listaCategoria;

    }

}
