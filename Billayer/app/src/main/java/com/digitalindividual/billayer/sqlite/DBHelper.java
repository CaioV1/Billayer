package com.digitalindividual.billayer.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 17170077 on 20/03/2018.
 */

public class DBHelper extends SQLiteOpenHelper{

    private static String dbNome = "billayer.db";

    private static int dbVersao = 1;

    public DBHelper(Context context){

        super(context, dbNome, null, dbVersao);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String SQL = "CREATE TABLE tbl_categoria(" +
                     "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "nome TEXT," +
                     "imagem BLOOB);";

        database.execSQL(SQL);

        SQL = "CREATE TABLE tbl_lancamento(" +
              "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
              "nome TEXT," +
              "valor REAL," +
              "data INTEGER," +
              "id_categoria INTEGER," +
              "descricao TEXT," +
              "tipo TEXT," +
              "FOREIGN KEY (id_categoria) REFERENCES tbl_categoria(_id));";

        database.execSQL(SQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int versaoVelha, int versaoNova) {

//        database.execSQL("DROP TABLE tbl_lancamento;");
//
//        database.execSQL("DROP TABLE tbl_categoria;");
//
//        onCreate(database);

    }
}
