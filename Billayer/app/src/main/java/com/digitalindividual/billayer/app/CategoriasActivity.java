package com.digitalindividual.billayer.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.digitalindividual.billayer.R;
import com.digitalindividual.billayer.adapters.CategoriaAdapter;
import com.digitalindividual.billayer.dao.CategoriaDAO;
import com.digitalindividual.billayer.models.Categoria;

import java.util.ArrayList;

public class CategoriasActivity extends AppCompatActivity {

    ListView listViewCategorias;

    CategoriaAdapter adapter;

    CategoriaDAO categoriaDAO = CategoriaDAO.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        listViewCategorias = (ListView) findViewById(R.id.list_view_categorias);

        adapter = new CategoriaAdapter(getApplicationContext(), new ArrayList<Categoria>());

        listViewCategorias.setAdapter(adapter);

    }

    @Override
    protected void onResume() {

        super.onResume();

        adapter.clear();

        ArrayList<Categoria> listaCategoria =  categoriaDAO.selecionarTodas(getApplicationContext());

        adapter.addAll(listaCategoria);
    }
}
