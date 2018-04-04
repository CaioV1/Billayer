package com.digitalindividual.billayer.models;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by 17170077 on 20/03/2018.
 */

public class Lancamento {

    int id;
    int idCategoria;
    String nome;
    double valor;
    Date data;
    String categoria;
    String descricao;
    String tipo;
    Bitmap imagemCategoria;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Bitmap getImagemCategoria() {
        return imagemCategoria;
    }

    public void setImagemCategoria(Bitmap imagemCategoria) {
        this.imagemCategoria = imagemCategoria;
    }

}
