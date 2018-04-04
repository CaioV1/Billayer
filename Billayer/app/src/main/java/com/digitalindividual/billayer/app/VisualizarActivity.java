package com.digitalindividual.billayer.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.digitalindividual.billayer.R;
import com.digitalindividual.billayer.dao.LancamentoDAO;
import com.digitalindividual.billayer.models.Lancamento;
import com.digitalindividual.billayer.util.ConversaoData;

public class VisualizarActivity extends AppCompatActivity {

    TextView txtNome;
    TextView txtValor;
    TextView txtCategoria;
    TextView txtData;
    TextView txtDescricao;

    ImageView imgCategoria;

    Lancamento lancamento = new Lancamento();

    LancamentoDAO lancamentoDAO = LancamentoDAO.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar);

        txtNome = (TextView) findViewById(R.id.txt_nome_visualizar);
        txtValor = (TextView) findViewById(R.id.txt_valor_visualizar);
        txtCategoria = (TextView) findViewById(R.id.txt_categoria_visualizar);
        txtData = (TextView) findViewById(R.id.txt_data_visualizar);
        txtDescricao = (TextView) findViewById(R.id.txt_descricao_visualizar);

        imgCategoria = (ImageView) findViewById(R.id.img_categoria_visualizar);

        int idLancamento;

        idLancamento = getIntent().getIntExtra("id", 0);

        ConversaoData conversaoData = new ConversaoData();

        lancamento = lancamentoDAO.selecionarUm(getApplicationContext(), idLancamento);

        txtNome.setText(lancamento.getNome());
        txtValor.setText(String.valueOf(lancamento.getValor()));
        txtCategoria.setText(lancamento.getCategoria());
        txtData.setText(conversaoData.dateParaString(lancamento.getData()));
        txtDescricao.setText(lancamento.getDescricao());
        imgCategoria.setImageBitmap(lancamento.getImagemCategoria());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_visualizar, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.menu_excluir){

            lancamentoDAO.remover(getApplicationContext(), lancamento.getId());

            finish();

        } else {

            Intent intent = new Intent(getApplicationContext(), Cadastro.class);

            String tipo = getIntent().getStringExtra("tipo");

            intent.putExtra("id", lancamento.getId());
            intent.putExtra("tipo", tipo);

            startActivity(intent);

        }

        return true;

    }
}
