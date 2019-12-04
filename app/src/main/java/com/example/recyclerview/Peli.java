package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import java.util.ArrayList;

public class Peli extends AppCompatActivity {

    ImageView ivImagen;
    TextView tvTitulo;
    TextView tvDescripcion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peli);

        ArrayList<PelisVO> listaPelis = new ArrayList<PelisVO>();
        listaPelis = (ArrayList<PelisVO>) getIntent().getSerializableExtra("lista");
        int pelicula =Integer.parseInt(getIntent().getSerializableExtra("Peli").toString());


        ivImagen = (ImageView) findViewById(R.id.idImagen);
        tvTitulo = (TextView) findViewById(R.id.idTitulo);
        tvDescripcion = (TextView) findViewById(R.id.idDescripcion);

        tvTitulo.setText(listaPelis.get(pelicula).getTitulo());
        tvDescripcion.setText(listaPelis.get(pelicula).getDescripcion());
        ivImagen.setImageResource(listaPelis.get(pelicula).getImagen());
    }
}
