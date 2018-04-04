package com.digitalindividual.billayer.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.digitalindividual.billayer.R;
import com.digitalindividual.billayer.models.Categoria;

import java.util.ArrayList;

/**
 * Created by 17170077 on 14/03/2018.
 */

public class SpinnerAdapter extends ArrayAdapter<Categoria> {

    public SpinnerAdapter(Context context, int idLayout, int idText, ArrayList<Categoria> listaCategoria){

        super(context, idText, listaCategoria);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.linha_spinner, null);

        }

        Categoria item = getItem(position);

        ImageView imgItem =  convertView.findViewById(R.id.image_spinner);
        TextView txtItem = convertView.findViewById(R.id.txt_nome_categoria);

        txtItem.setText(item.getNome());

        if (item.getImagem() != null){

            imgItem.setImageBitmap(item.getImagem());

        }

        return convertView;

    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return getView(position, convertView, parent);

    }
}
