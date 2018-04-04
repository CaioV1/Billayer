package com.digitalindividual.billayer.models;

import android.graphics.Bitmap;

/**
 * Created by 17170077 on 21/03/2018.
 */

public class Categoria {

    int id;
    String nome;
    Bitmap imagem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Bitmap getImagem() {
        return imagem;
    }

    public void setImagem(Bitmap imagem) {
        this.imagem = imagem;
    }
}
