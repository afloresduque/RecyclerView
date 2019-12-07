package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<PelisVO> listPelis = new ArrayList<PelisVO>();
    ArrayList<PelisVO> listaPelisFiltrada = new ArrayList<>();
    RecyclerView recyclerPelis;
    boolean order=false;
    AdaptadorPelis adapter;
    boolean mostrandoFavoritos = false;
    private MenuItem securedConnection;
    //Button favorito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        llenarPelis();
        construirRecycler(order);
    }

    private void llenarPelis() {
        listPelis.add(new PelisVO(getResources().getString(R.string.elpianista_titulo),getResources().getString(R.string.elpianista_descripBreve),getResources().getString(R.string.elpianista_descrip),R.drawable.elpianista,getResources().getString(R.string.elpianista_genero), getResources().getString(R.string.elpianista_direccion)));
        listPelis.add(new PelisVO(getResources().getString(R.string.avatar_titulo),getResources().getString(R.string.avatar_descripBreve),getResources().getString(R.string.avatar_descrip),R.drawable.avatar, getResources().getString(R.string.avatar_genero), getResources().getString(R.string.avatar_direccion)));
        listPelis.add(new PelisVO(getResources().getString(R.string.forrestgump_titulo),getResources().getString(R.string.forrestgump_descripBreve),getResources().getString(R.string.forrestgump_descrip),R.drawable.forrestgump, getResources().getString(R.string.forrestgump_genero), getResources().getString(R.string.forrestgump_direccion)));
        listPelis.add(new PelisVO(getResources().getString(R.string.gladiator_titulo),getResources().getString(R.string.gladiator_descripBreve),getResources().getString(R.string.gladiator_descrip),R.drawable.gladiator,getResources().getString(R.string.gladiator_genero), getResources().getString(R.string.gladiator_direccion)));
        listPelis.add(new PelisVO(getResources().getString(R.string.lalistadeschindler_titulo),getResources().getString(R.string.lalistadeschindler_descripBreve),getResources().getString(R.string.lalistadeschindler_descrip),R.drawable.lalistadeschindler, getResources().getString(R.string.lalistadeschindler_genero),getResources().getString(R.string.lalistadeschindler_direccion)));
        listPelis.add(new PelisVO(getResources().getString(R.string.mejorimposible_titulo),getResources().getString(R.string.mejorimposible_descripBreve),getResources().getString(R.string.mejorimposible_descrip),R.drawable.mejorimposible,getResources().getString(R.string.mejorimposible_genero), getResources().getString(R.string.mejorimposible_direccion)));
        listPelis.add(new PelisVO(getResources().getString(R.string.lavidaesbella_titulo),getResources().getString(R.string.lavidaesbella_descripBreve),getResources().getString(R.string.lavidaesbella_descrip),R.drawable.lavidaesbella,getResources().getString(R.string.lavidaesbella_genero),getResources().getString(R.string.lavidaesbella_direccion)));
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLista: Utilidades.visualizacion= Utilidades.lista;
                break;
            case R.id.btnGrid: Utilidades.visualizacion=Utilidades.grid;
                break;
        }
        construirRecycler(order);
    }

    private void construirRecycler(boolean order) {
        recyclerPelis = (RecyclerView) findViewById(R.id.RecyclerId);

        if(Utilidades.visualizacion== Utilidades.lista){
            recyclerPelis.setLayoutManager(new LinearLayoutManager(this));
        }else{
            recyclerPelis.setLayoutManager(new GridLayoutManager(this,2));
        }

        adapter = new AdaptadorPelis(listPelis);

        if (order){
            Collections.sort(listPelis, new CustomComparator());
        }else {
            Collections.shuffle(listPelis, new CustomComparator());
        }


        final Intent intentPeli = new Intent(this, Peli.class);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intentPeli.putExtra("lista", adapter.getListaPelis());
                intentPeli.putExtra("Peli", adapter.getListaPelis().get(recyclerPelis.getChildAdapterPosition(v)));
                startActivity(intentPeli);
            }
        });
        recyclerPelis.setAdapter(adapter);
    }


    public class CustomComparator extends Random implements Comparator<PelisVO> {
        @Override
        public int compare(PelisVO p1, PelisVO p2){
            return p1.getTitulo().compareTo(p2.getTitulo());
        }
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.action_bar_inicio, menu);
        securedConnection = menu.getItem(0);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem peli) {
        //Log.d("onOptionesItemsSelected", "entra o no?");
        switch (peli.getItemId()){
            case R.id.accion_nuevo:
                Intent nueva = new Intent(this, NuevaPeli.class);
                startActivityForResult(nueva, 2);
                return true;

            case R.id.accion_filtrar:
                Intent filtrar = new Intent(this, PantallaFiltrar.class);
                startActivityForResult(filtrar, 3);
                return true;

            case R.id.accion_favoritos:
                if (!mostrandoFavoritos){
                    adapter.setListaPelis(GestionarFavoritos.listaFavoritos);
                    adapter.notifyDataSetChanged();
                    mostrandoFavoritos=true;
                    peli.setIcon(R.drawable.star);
                    if(GestionarFavoritos.listaFavoritos.size()==0)
                        Toast.makeText(this, R.string.noHayFavoritos, Toast.LENGTH_SHORT).show();
                }else{
                    adapter.setListaPelis(listPelis);
                    adapter.notifyDataSetChanged();
                    recyclerPelis.setAdapter(adapter);
                    peli.setIcon(R.drawable.starvacia);
                    mostrandoFavoritos=false;
                }
                return true;

            case R.id.accion_ordenar:
                order = !order;
                construirRecycler(order);
                return true;

            case R.id.accion_volverAcargar:
                construirRecycler(order);
                securedConnection.setVisible(false);
                return true;


            default:
                return super.onOptionsItemSelected(peli);

        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                if (resultCode == Activity.RESULT_OK && requestCode==2) {

                    PelisVO nuevaPeli = (PelisVO) data.getSerializableExtra("nuevaPeli");

                    //Log.e("onOptionesItemsSelected", "prueba");
                    //String ruta = data.getStringExtra("uri");
                    //nuevaPeli.setImagenU((Uri) ruta);
                    //nuevaPeli.setImagenD((Drawable) data.getSerializableExtra("imagen"));

                    if(nuevaPeli.isFavorito()){
                        GestionarFavoritos.listaFavoritos.add(nuevaPeli);
                    }

                    //Log.e("eeeeeeeeeeeeeee", nuevaPeli.getImagenU());
                    //Log.e("eeeeeeeeeeeeeee", nuevaPeli.getImagenU().toString());

                    listPelis.add(nuevaPeli);
                    adapter.notifyDataSetChanged();
                    //recyclerPelis.setAdapter(adapter);
                }
                else if(resultCode == Activity.RESULT_OK && requestCode==3){
                    ArrayList<String> generos = (ArrayList<String>) data.getSerializableExtra("generos");
                    //Toast.makeText(this, generos.get(0), Toast.LENGTH_LONG).show();
                    int tmaaño = generos.size();
                    if(generos.size()==1)
                        Toast.makeText(this,generos.get(0), Toast.LENGTH_LONG).show();
                    listaPelisFiltrada.removeAll(listaPelisFiltrada);

                    for(int i=0;i<listPelis.size();i++){

                        for(int j=0;j<generos.size();j++){

                            if(listPelis.get(i).getGenero().equals(generos.get(j))){
                                listaPelisFiltrada.add(listPelis.get(i));
                            }
                        }
                    }

                    if(listaPelisFiltrada.isEmpty()){
                        Toast.makeText(this,"No hay películas de ese género", Toast.LENGTH_LONG).show();
                    }
                    securedConnection.setVisible(true);
                    adapter.setListaPelis(listaPelisFiltrada);
                    adapter.notifyDataSetChanged();
                }

                else {
                    recyclerPelis.setAdapter(adapter);
                }
    }


}
