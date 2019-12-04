package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import static com.example.recyclerview.Utilidades.lista;

public class MainActivity extends AppCompatActivity {

    ArrayList<PelisVO> listPelis;
    RecyclerView recyclerPelis;
    boolean order=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        construirRecycler(order);
    }

    private void llenarPelis() {
        listPelis.add(new PelisVO("El Pianista",getResources().getString(R.string.elpianista_descripBreve),getResources().getString(R.string.elpianista_descrip),R.drawable.elpianista,"Drama"));
        listPelis.add(new PelisVO("Avatar",getResources().getString(R.string.avatar_descripBreve),getResources().getString(R.string.avatar_descrip),R.drawable.avatar, "Cienciaficción"));
        listPelis.add(new PelisVO("Forrest Gump",getResources().getString(R.string.forrestgump_descripBreve),getResources().getString(R.string.forrestgump_descrip),R.drawable.forrestgump, "Drama"));
        listPelis.add(new PelisVO("Gladiator",getResources().getString(R.string.gladiator_descripBreve),getResources().getString(R.string.gladiator_descrip),R.drawable.gladiator,"Drama"));
        listPelis.add(new PelisVO("La Lista de Schindler",getResources().getString(R.string.lalistadeschindler_descripBreve),getResources().getString(R.string.lalistadeschindler_descrip),R.drawable.lalistadeschindler, "Histórica"));
        listPelis.add(new PelisVO("Mejor... Imposible",getResources().getString(R.string.mejorimposible_descripBreve),getResources().getString(R.string.mejorimposible_descrip),R.drawable.mejorimposible,"Comedia"));
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLista: Utilidades.visualizacion= lista;
                break;
            case R.id.btnGrid: Utilidades.visualizacion=Utilidades.grid;
                break;
        }
        construirRecycler(order);
    }

    private void construirRecycler(boolean order) {
        listPelis = new ArrayList<PelisVO>();
        recyclerPelis = (RecyclerView) findViewById(R.id.RecyclerId);

        if(Utilidades.visualizacion== lista){
            recyclerPelis.setLayoutManager(new LinearLayoutManager(this));
        }else{
            recyclerPelis.setLayoutManager(new GridLayoutManager(this,3));
        }
        llenarPelis();

        AdaptadorPelis adapter = new AdaptadorPelis(listPelis);

        if (order){
            Collections.sort(listPelis, new CustomComparator());
        }else {
            Collections.shuffle(listPelis, new CustomComparator());
        }


        final Intent intentPeli = new Intent(this, Peli.class);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intentPeli.putExtra("lista", listPelis);
                intentPeli.putExtra("Peli", String.valueOf(recyclerPelis.getChildAdapterPosition(v)));
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
        return true;
    }

    //@Override
    public boolean onOptionesItemsSelected(MenuItem peli) {
        switch (peli.getItemId()){
            case R.id.accion_nuevo:
                return true;
            case R.id.accion_filtrar:
                return true;
            case R.id.accion_favoritos:
                return true;
            case R.id.accion_ordenar:
                order = !order;
                construirRecycler(order);
                return true;
            default:
                return super.onOptionsItemSelected(peli);

        }
    }


}
