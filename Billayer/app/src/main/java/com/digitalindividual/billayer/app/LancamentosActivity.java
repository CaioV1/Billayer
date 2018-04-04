package com.digitalindividual.billayer.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.digitalindividual.billayer.R;
import com.digitalindividual.billayer.adapters.LancamentoAdapter;
import com.digitalindividual.billayer.dao.CategoriaDAO;
import com.digitalindividual.billayer.dao.LancamentoDAO;
import com.digitalindividual.billayer.models.Categoria;
import com.digitalindividual.billayer.models.Lancamento;

import java.util.ArrayList;

public class LancamentosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listViewLancamento;

    LancamentoAdapter adapter;

    LancamentoDAO lancamentoDAO;

    TextView txtTitulo;

    String tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancamentos);

        lancamentoDAO = lancamentoDAO.getInstance();

        listViewLancamento = (ListView) findViewById(R.id.list_view_lancamento);

        txtTitulo = (TextView) findViewById(R.id.txt_titulo_lancamento);

        String tipo = getIntent().getStringExtra("Tipo");

        if(tipo.equals("receita")){

            txtTitulo.setText("Receitas");

        } else {

            txtTitulo.setText("Despesas");

        }

        adapter = new LancamentoAdapter(getApplicationContext(), new ArrayList<Lancamento>());

        listViewLancamento.setAdapter(adapter);

        listViewLancamento.setOnItemClickListener(this);

    }

    @Override
    protected void onResume() {

        super.onResume();

        ArrayList<Lancamento> listaLancamentos = new ArrayList<>();

        tipo = getIntent().getStringExtra("Tipo");

        if(tipo.equals("receita")){

            listaLancamentos = lancamentoDAO.selecionarReceitas(this);

        } else {

            listaLancamentos = lancamentoDAO.selecionarDespesas(this);

        }

        adapter.clear();

        adapter.addAll(listaLancamentos);

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Lancamento lancamento = adapter.getItem(i);

        Toast.makeText(this, String.valueOf(lancamento.getId()), Toast.LENGTH_LONG).show();

        Intent intent =  new Intent(this, VisualizarActivity.class);

        intent.putExtra("id", lancamento.getId());
        intent.putExtra("tipo", tipo);

        startActivity(intent);

    }
}
