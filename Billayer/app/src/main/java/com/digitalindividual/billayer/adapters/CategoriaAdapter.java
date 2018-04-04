package com.digitalindividual.billayer.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.digitalindividual.billayer.R;
import com.digitalindividual.billayer.app.CadastroCategoria;
import com.digitalindividual.billayer.app.CategoriasActivity;
import com.digitalindividual.billayer.dao.CategoriaDAO;
import com.digitalindividual.billayer.dao.LancamentoDAO;
import com.digitalindividual.billayer.models.Categoria;
import com.digitalindividual.billayer.models.Lancamento;

import java.util.ArrayList;

/**
 * Created by 17170077 on 03/04/2018.
 */

public class CategoriaAdapter extends ArrayAdapter<Categoria> {

    private ArrayList<Categoria> listaCategoria;
    private CategoriaAdapter categoriaAdapter;
    private LancamentoDAO lancamentoDAO = LancamentoDAO.getInstance();
    private ArrayList<Lancamento> listaLancamentos = new ArrayList<>();
    private Context context;
    boolean categoriaRegistrada;

    public CategoriaAdapter(Context context, ArrayList<Categoria> listaCategoria){

        super(context, 0, listaCategoria);

        this.context = context;
        this.listaCategoria = listaCategoria;
        this.categoriaAdapter = this;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_categoria, null);

        }

        final Categoria categoria = getItem(position);

        final CategoriaDAO categoriaDAO = CategoriaDAO.getInstance();

        ImageView imgCategoria = convertView.findViewById(R.id.img_categoria_list);

        TextView txtCategoria = convertView.findViewById(R.id.txt_categoria_list);

        ImageButton btnEditar = convertView.findViewById(R.id.btn_editar_categoria);
        ImageButton btnExcluir = convertView.findViewById(R.id.btn_excluir_categoria);

        final View finalConvertView = convertView;

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(finalConvertView.getContext(), CadastroCategoria.class);

                intent.putExtra("id", categoria.getId());

                context.startActivity(intent);

            }
        });

        listaLancamentos = lancamentoDAO.selecionarTodos(convertView.getContext());

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i = 0; i < listaLancamentos.size(); i++){

                    if(categoria.getId() == listaLancamentos.get(i).getIdCategoria()){

                        categoriaRegistrada = true;

                    } else {

                        categoriaRegistrada = false;

                    }

                }

                if(categoriaRegistrada){

                    Toast.makeText(finalConvertView.getContext(), "Esta categoria está registrada em um lançamento. Delete o lançamento se realmente deseja apagar a categoria.", Toast.LENGTH_LONG).show();

                } else {

                    categoriaDAO.remover(finalConvertView.getContext(), categoria.getId());

                    listaCategoria.remove(categoria);

                    categoriaAdapter.notifyDataSetChanged();

                }

            }
        });

        imgCategoria.setImageBitmap(categoria.getImagem());
        txtCategoria.setText(categoria.getNome());

        return convertView;

    }
}

