package com.digitalindividual.billayer.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.digitalindividual.billayer.R;
import com.digitalindividual.billayer.dao.CategoriaDAO;
import com.digitalindividual.billayer.models.Categoria;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class CadastroCategoria extends AppCompatActivity {

    ImageView imgCategoria;

    Button btnCadastrar;

    EditText txtNome;

    TextView txtTitulo;

    int COD_GALERIA = 1;

    Bitmap imagem;

    CategoriaDAO categoriaDAO = CategoriaDAO.getInstance();

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_categoria);

        imgCategoria = (ImageView) findViewById(R.id.img_categoria_cadastrar);

        btnCadastrar = (Button) findViewById(R.id.btn_cadastrar_categoria);

        txtNome = (EditText) findViewById(R.id.txt_categoria_cadastrar);

        txtTitulo = (TextView) findViewById(R.id.txt_titulo_categoria);

        id = getIntent().getIntExtra("id", 0);

        Categoria categoria = categoriaDAO.selecionarUm(getApplicationContext(), id);

        if(id != 0){

            imgCategoria.setImageBitmap(categoria.getImagem());
            txtNome.setText(categoria.getNome());
            txtTitulo.setText("Atualizar Categoria");
            btnCadastrar.setText("Atualizar");

        }

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Categoria categoria = new Categoria();

                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgCategoria.getDrawable();

                categoria.setImagem(bitmapDrawable.getBitmap());
                categoria.setNome(txtNome.getText().toString());

                if(id == 0) {

                    categoriaDAO.inserir(getApplicationContext(), categoria);

                } else {

                    categoria.setId(id);
                    categoriaDAO.atualizar(getApplicationContext(), categoria);

                }

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent);

            }
        });

    }

    public void abrirGaleria(View view){

        Intent intent =  new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");

        startActivityForResult(intent, COD_GALERIA);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode ==  COD_GALERIA){

            if(resultCode == Activity.RESULT_OK){

                try{

                    InputStream inputStream = getContentResolver().openInputStream(data.getData());

                    imagem = BitmapFactory.decodeStream(inputStream);

                    imgCategoria.setImageBitmap(imagem);

                } catch (FileNotFoundException e){

                    e.printStackTrace();

                }

            }

        }
    }
}
