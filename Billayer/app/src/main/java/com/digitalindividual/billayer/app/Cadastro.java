package com.digitalindividual.billayer.app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.digitalindividual.billayer.R;
import com.digitalindividual.billayer.dao.CategoriaDAO;
import com.digitalindividual.billayer.dao.LancamentoDAO;
import com.digitalindividual.billayer.models.Categoria;
import com.digitalindividual.billayer.models.Lancamento;
import com.digitalindividual.billayer.util.ConversaoData;

import java.util.ArrayList;

public class Cadastro extends AppCompatActivity {

    LancamentoDAO lancamentoDAO =  new LancamentoDAO();

    Lancamento lancamento =  new Lancamento();

    CategoriaDAO categoriaDAO = new CategoriaDAO();

    Categoria categoria = new Categoria();

    ConversaoData conversaoData = new ConversaoData();

    TextView txtTitulo;
    TextView txtNomeCategoria;
    TextView txtNomeTitulo;

    EditText txtNome;
    EditText txtValor;
    EditText txtData;
    EditText txtDescricao;

    Spinner spiCategoria;

    Button btnSalvar;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        txtTitulo = (TextView) findViewById(R.id.txt_titulo);
        txtNomeTitulo = (TextView) findViewById(R.id.txt_nome_titulo);

        txtNome = (EditText) findViewById(R.id.txt_nome);
        txtValor = (EditText) findViewById(R.id.txt_valor);
        txtData = (EditText) findViewById(R.id.txt_data);
        txtDescricao = (EditText) findViewById(R.id.txt_descricao);

        btnSalvar = (Button) findViewById(R.id.btn_salvar);

        spiCategoria = (Spinner) findViewById(R.id.spi_categoria);

        final String tipo = getIntent().getStringExtra("tipo");

        id = getIntent().getIntExtra("id", 0);

        if(tipo.equals("receita")){

            if(id == 0){

                txtTitulo.setText("Cadastro Receita");
                btnSalvar.setText("Cadastrar Receita");

            } else {

                txtTitulo.setText("Atualizar Receita");
                btnSalvar.setText("Atualizar Receita");

            }

            txtNomeTitulo.setText("Nome da Receita");
            txtNome.setHint("Digite o nome da receita");
            txtValor.setHint("Digite o valor do receita");
            txtData.setHint("Digite a data do receita");
            txtDescricao.setHint("Descreva o receita");

        } else {

            if (id == 0) {

                txtTitulo.setText("Cadastro Despesa");
                btnSalvar.setText("Cadastrar Despesa");

            } else {

                txtTitulo.setText("Atualizar Despesa");
                btnSalvar.setText("Atualizar Despesa");

            }

            txtNomeTitulo.setText("Nome da Despesa");
            txtNome.setHint("Digite o nome da despesa");
            txtValor.setHint("Digite o valor da despesa");
            txtData.setHint("Digite a data da despesa");
            txtDescricao.setHint("Descreva a despesa");

        }

        //TODO Carregar os dados enviados da Activity Visualizar

        ArrayList<Categoria> listaCategoria = categoriaDAO.selecionarTodas(getApplicationContext());

        txtNomeCategoria = (TextView) findViewById(R.id.txt_nome_categoria);

        SpinnerAdapter adapter = new com.digitalindividual.billayer.adapters.SpinnerAdapter(this, R.layout.linha_spinner, R.id.txt_nome_categoria, listaCategoria);

        spiCategoria.setAdapter(adapter);

        spiCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                categoria = (Categoria) spiCategoria.getSelectedItem();

                Toast.makeText(getApplicationContext(), String.valueOf(categoria.getId()), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lancamento = new Lancamento();

        lancamento = lancamentoDAO.selecionarUm(getApplicationContext(), id);

        if(id != 0){

            txtNome.setText(lancamento.getNome());
            txtValor.setText(String.valueOf(lancamento.getValor()));
            txtData.setText(conversaoData.dateParaString(lancamento.getData()));
            txtDescricao.setText(lancamento.getDescricao());

            spiCategoria.setSelection(posicaoItem(spiCategoria, lancamento.getIdCategoria()));

        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                lancamento = new Lancamento();

                categoria = (Categoria) spiCategoria.getSelectedItem();

                lancamento.setId(id);
                lancamento.setNome(txtNome.getText().toString());
                lancamento.setValor(Double.parseDouble(txtValor.getText().toString()));
                lancamento.setData(conversaoData.stringParaDate(txtData.getText().toString()));
                lancamento.setDescricao(txtDescricao.getText().toString());
                lancamento.setIdCategoria(categoria.getId());
                lancamento.setTipo(tipo);

                if(id == 0){

                    lancamentoDAO.inserir(getApplicationContext(), lancamento);

                } else {

                    lancamentoDAO.atualizar(getApplicationContext(), lancamento);

                }

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent);

            }
        });

    }

    public int posicaoItem(Spinner spinner, int idItem){

        Categoria categoria = new Categoria();

        int posicao = 0;

        for(int i = 0; i < spinner.getAdapter().getCount(); i++){

            categoria = (Categoria) spinner.getItemAtPosition(i);

            if(idItem == categoria.getId()) {

                posicao = i;

            }
        }

        return posicao;

    }

}
