package com.example.recyclerview;
import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

public class GestionarFavoritos {

    static ArrayList<PelisVO> listaFavoritos = new ArrayList<>();

    public static void cambiarFavoritos(PelisVO peli, Context context) {

        boolean anadido = false;

        for (int i = 0; i < listaFavoritos.size(); i++ ) {
            if (listaFavoritos.get(i).getTitulo() == peli.getTitulo()){
                anadido = true;
                break;
            }
        }

        if (!anadido) {
            peli.setFavorito(true);
            listaFavoritos.add(peli);
            Toast.makeText(context, R.string.anadidoFavorito, Toast.LENGTH_SHORT).show();
        } else {
            peli.setFavorito(false);
            listaFavoritos.remove(peli);
            Toast.makeText(context, R.string.eliminadaFavorito, Toast.LENGTH_SHORT).show();
        }
    }

}
