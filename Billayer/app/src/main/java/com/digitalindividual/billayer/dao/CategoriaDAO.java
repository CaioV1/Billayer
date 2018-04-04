package com.digitalindividual.billayer.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.digitalindividual.billayer.models.Categoria;
import com.digitalindividual.billayer.sqlite.DBHelper;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by 17170077 on 21/03/2018.
 */

public class CategoriaDAO {

    private static CategoriaDAO instance;

    public static CategoriaDAO getInstance(){

        if(instance == null){

            instance = new CategoriaDAO();

        }

        return instance;

    }

    public Boolean inserir(Context context, Categoria categoria){

        SQLiteDatabase database = new DBHelper(context).getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("nome", categoria.getNome());
        values.put("imagem", turnToBytes(categoria.getImagem()));

        long id = database.insert("tbl_categoria", null, values);

        if (id != -1){

            return true;

        } else {

            return false;

        }
    }

    public ArrayList<Categoria> selecionarTodas(Context context){

        ArrayList<Categoria> listaCategorias = new ArrayList<>();

        SQLiteDatabase database = new DBHelper(context).getReadableDatabase();

        String SQL = "SELECT * FROM tbl_categoria;";

        Cursor cursor = database.rawQuery(SQL, null);

        while(cursor.moveToNext()){

            Categoria categoria = new Categoria();

            categoria.setId(cursor.getInt(0));
            categoria.setNome(cursor.getString(1));

            byte[] byteImagem = cursor.getBlob(2);

            if(byteImagem != null && byteImagem.length > 0){

                categoria.setImagem(turnToBitmap(byteImagem));

            }

            listaCategorias.add(categoria);

        }

        cursor.close();

        return listaCategorias;

    }

    public Categoria selecionarUm(Context context, int id){

        Categoria categoria = null;

        SQLiteDatabase database = new DBHelper(context).getReadableDatabase();

        String SQL = "SELECT * FROM tbl_categoria WHERE _id = " + id + ";";

        Cursor cursor = database.rawQuery(SQL, null);

        if(cursor.getCount() > 0){

            cursor.moveToFirst();

            categoria = new Categoria();

            categoria.setId(cursor.getInt(0));
            categoria.setNome(cursor.getString(1));
            categoria.setImagem(turnToBitmap(cursor.getBlob(2)));

        }

        cursor.close();

        return categoria;

    }

    public Boolean remover(Context context, int id){

        SQLiteDatabase database = new DBHelper(context).getWritableDatabase();

        database.delete("tbl_categoria", "_id = ?", new String[]{String.valueOf(id)});

        return true;

    }

    public Boolean atualizar(Context context, Categoria categoria){

        SQLiteDatabase database = new DBHelper(context).getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("nome", categoria.getNome());
        values.put("imagem", turnToBytes(categoria.getImagem()));

        database.update("tbl_categoria", values,"_id = ?", new String[]{String.valueOf(categoria.getId())});

        return true;

    }

    private byte[] turnToBytes(Bitmap bitmap){

        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);

        return stream.toByteArray();

    }

    private  Bitmap turnToBitmap(byte[] img){

        return BitmapFactory.decodeByteArray(img,0,img.length);

    }

}
