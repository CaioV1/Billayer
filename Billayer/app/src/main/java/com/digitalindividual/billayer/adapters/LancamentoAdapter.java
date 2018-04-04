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
import com.digitalindividual.billayer.models.Lancamento;
import com.digitalindividual.billayer.util.ConversaoData;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by 17170077 on 20/03/2018.
 */

public class LancamentoAdapter extends ArrayAdapter<Lancamento> {

    public LancamentoAdapter(Context context, ArrayList<Lancamento> listaLamcamento){

        super(context, 0, listaLamcamento);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_lancamento, null);

        }

        Lancamento lancamento = getItem(position);

        ConversaoData conversaoData =  new ConversaoData();

        TextView txtValor = convertView.findViewById(R.id.txt_item_valor);
        TextView txtData = convertView.findViewById(R.id.txt_item_data);
        TextView txtCategoria = convertView.findViewById(R.id.txt_item_categoria);
        TextView txtNome = convertView.findViewById(R.id.txt_item_nome);
        ImageView imgCategoria = convertView.findViewById(R.id.img_categoria);

        String data = conversaoData.dateParaString(lancamento.getData());

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "br"));

        txtNome.setText(lancamento.getNome());
        txtValor.setText(numberFormat.format(lancamento.getValor()));
        txtData.setText(data);
        txtCategoria.setText(lancamento.getCategoria());

        if (lancamento.getImagemCategoria() != null){

            imgCategoria.setImageBitmap(lancamento.getImagemCategoria());

        }

        return convertView;

    }
}
