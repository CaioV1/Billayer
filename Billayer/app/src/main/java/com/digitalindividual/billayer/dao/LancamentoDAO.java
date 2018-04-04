package com.digitalindividual.billayer.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;

import com.digitalindividual.billayer.models.Lancamento;
import com.digitalindividual.billayer.sqlite.DBHelper;
import com.digitalindividual.billayer.util.ConversaoData;

import java.util.ArrayList;

/**
 * Created by 17170077 on 20/03/2018.
 */

public class LancamentoDAO {

    ConversaoData conversaoData;

    private static LancamentoDAO instance;

    public static LancamentoDAO getInstance(){

        if (instance == null){

            instance = new LancamentoDAO();

        }

        return instance;

    }

    public Boolean inserir (Context context, Lancamento lancamento){

        SQLiteDatabase database = new DBHelper(context).getWritableDatabase();

        ContentValues values = new ContentValues();

        conversaoData = new ConversaoData();

        int dataSQLite = conversaoData.dateParaSQLite(lancamento.getData());

        values.put("nome", lancamento.getNome());
        values.put("valor", lancamento.getValor());
        values.put("data", dataSQLite);
        values.put("id_categoria", lancamento.getIdCategoria());
        values.put("descricao", lancamento.getDescricao());
        values.put("tipo", lancamento.getTipo());

        long id = database.insert("tbl_lancamento", null, values);

        if (id != -1){

            return true;

        } else {

            return false;

        }

    }

    public ArrayList<Lancamento> selecionarTodos(Context context){

        ArrayList<Lancamento> listaLancamentos =  new ArrayList<>();

        SQLiteDatabase database =  new DBHelper(context).getReadableDatabase();

        String SQL = "SELECT l._id, l.valor, l.data, l.descricao, l.tipo, c.nome, c.imagem, l.nome, l.id_categoria " +
                "FROM tbl_lancamento AS l INNER JOIN tbl_categoria AS c ON l.id_categoria = c._id;";

        Cursor cursor = database.rawQuery(SQL, null);

        conversaoData = new ConversaoData();

        while(cursor.moveToNext()){

            Lancamento lancamento = new Lancamento();

            lancamento.setId(cursor.getInt(0));
            lancamento.setValor(cursor.getDouble(1));
            lancamento.setData(conversaoData.sqliteParaDate(cursor.getInt(2)));
            lancamento.setDescricao(cursor.getString(3));
            lancamento.setTipo(cursor.getString(4));
            lancamento.setCategoria(cursor.getString(5));

            byte[] imagem = cursor.getBlob(6);

            lancamento.setImagemCategoria(BitmapFactory.decodeByteArray(imagem, 0, imagem.length));

            lancamento.setNome(cursor.getString(7));

            lancamento.setIdCategoria(cursor.getInt(8));

            listaLancamentos.add(lancamento);

        }

        cursor.close();

        return listaLancamentos;

    }

    public ArrayList<Lancamento> selecionarReceitas(Context context){

        ArrayList<Lancamento> listaLancamentos =  new ArrayList<>();

        SQLiteDatabase database =  new DBHelper(context).getReadableDatabase();

        String SQL = "SELECT l._id, l.valor, l.data, l.descricao, l.tipo, c.nome, c.imagem, l.nome " +
                     "FROM tbl_lancamento AS l INNER JOIN tbl_categoria AS c ON l.id_categoria = c._id WHERE tipo = 'receita';";

        Cursor cursor = database.rawQuery(SQL, null);

        conversaoData = new ConversaoData();

        while(cursor.moveToNext()){

            Lancamento lancamento = new Lancamento();

            lancamento.setId(cursor.getInt(0));
            lancamento.setValor(cursor.getDouble(1));
            lancamento.setData(conversaoData.sqliteParaDate(cursor.getInt(2)));
            lancamento.setDescricao(cursor.getString(3));
            lancamento.setTipo(cursor.getString(4));
            lancamento.setCategoria(cursor.getString(5));

            byte[] imagem = cursor.getBlob(6);

            lancamento.setImagemCategoria(BitmapFactory.decodeByteArray(imagem, 0, imagem.length));

            lancamento.setNome(cursor.getString(7));

            listaLancamentos.add(lancamento);

        }

        cursor.close();

        return listaLancamentos;

    }

    public ArrayList<Lancamento> selecionarDespesas(Context context){

        ArrayList<Lancamento> listaLancamentos =  new ArrayList<>();

        SQLiteDatabase database =  new DBHelper(context).getReadableDatabase();

        String SQL = "SELECT l._id, l.valor, l.data, l.descricao, l.tipo, c.nome, c.imagem, l.nome " +
                     "FROM tbl_lancamento AS l INNER JOIN tbl_categoria AS c ON l.id_categoria = c._id WHERE tipo = 'despesa';";

        Cursor cursor = database.rawQuery(SQL, null);

        conversaoData = new ConversaoData();

        while(cursor.moveToNext()){

            Lancamento lancamento = new Lancamento();

            lancamento.setId(cursor.getInt(0));
            lancamento.setValor(cursor.getDouble(1));
            lancamento.setData(conversaoData.sqliteParaDate(cursor.getInt(2)));
            lancamento.setDescricao(cursor.getString(3));
            lancamento.setTipo(cursor.getString(4));
            lancamento.setCategoria(cursor.getString(5));

            byte[] imagem = cursor.getBlob(6);

            lancamento.setImagemCategoria(BitmapFactory.decodeByteArray(imagem, 0, imagem.length));

            lancamento.setNome(cursor.getString(7));

            listaLancamentos.add(lancamento);

        }

        cursor.close();

        return listaLancamentos;

    }

    public Lancamento selecionarUm(Context context, int idLancamento){

        SQLiteDatabase database =  new DBHelper(context).getReadableDatabase();

        Lancamento lancamento = null;

        String SQL = "SELECT l._id, l.valor, l.data, l.descricao, l.tipo, c.nome, c.imagem, l.nome, l.id_categoria " +
                     "FROM tbl_lancamento AS l INNER JOIN tbl_categoria AS c ON l.id_categoria = c._id " +
                     "WHERE l._id = " + idLancamento + ";";

        Cursor cursor = database.rawQuery(SQL, null);

        conversaoData = new ConversaoData();

        if(cursor.getCount() > 0){

            cursor.moveToFirst();

            lancamento = new Lancamento();

            lancamento.setId(cursor.getInt(0));
            lancamento.setValor(cursor.getDouble(1));
            lancamento.setData(conversaoData.sqliteParaDate(cursor.getInt(2)));
            lancamento.setDescricao(cursor.getString(3));
            lancamento.setTipo(cursor.getString(4));
            lancamento.setCategoria(cursor.getString(5));

            byte[] imagem = cursor.getBlob(6);

            lancamento.setImagemCategoria(BitmapFactory.decodeByteArray(imagem, 0, imagem.length));

            lancamento.setNome(cursor.getString(7));
            lancamento.setIdCategoria(cursor.getInt(8));

        }

        cursor.close();

        return lancamento;

    }

    public Boolean remover(Context context, int id){

        SQLiteDatabase database = new DBHelper(context).getWritableDatabase();

        database.delete("tbl_lancamento", "_id = ?", new String[]{String.valueOf(id)});

        return true;

    }

    public Boolean atualizar (Context context, Lancamento lancamento){

        SQLiteDatabase database = new DBHelper(context).getWritableDatabase();

        ContentValues values = new ContentValues();

        conversaoData = new ConversaoData();

        int dataSQLite = conversaoData.dateParaSQLite(lancamento.getData());

        values.put("nome", lancamento.getNome());
        values.put("valor", lancamento.getValor());
        values.put("data", dataSQLite);
        values.put("id_categoria", lancamento.getIdCategoria());
        values.put("descricao", lancamento.getDescricao());
        values.put("tipo", lancamento.getTipo());

        database.update("tbl_lancamento", values, "_id = ?", new String[]{String.valueOf(lancamento.getId())});

        return true;

    }

}
