package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<PelisVO> listPelis;
    RecyclerView recyclerPelis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        construirRecycler();
    }

    private void llenarPelis() {
        listPelis.add(new PelisVO("El Pianista",getResources().getString(R.string.elpianista_descripBreve),getResources().getString(R.string.elpianista_descripcion),R.drawable.elpianista,"Drama"));
        listPelis.add(new PelisVO("Avatar",getResources().getString(R.string.avatar_descripBreve),R.drawable.avatar, "Cienciaficción"));
        listPelis.add(new PelisVO("Forrest Gump",getResources().getString(R.string.forrestgump_descripBreve),R.drawable.forrestgump, "Drama"));
        listPelis.add(new PelisVO("Gladiator",getResources().getString(R.string.gladiator_descripBreve),R.drawable.gladiator,"Drama"));
        listPelis.add(new PelisVO("La Lista de Schindler",getResources().getString(R.string.lalistadeschindler_descripBreve),R.drawable.lalistadeschindler, "Histórica"));
        listPelis.add(new PelisVO("Mejor... Imposible",getResources().getString(R.string.mejorimposible_descripBreve),R.drawable.mejorimposible,"Comedia"));
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLista: Utilidades.visualizacion=Utilidades.lista;
                break;
            case R.id.btnGrid: Utilidades.visualizacion=Utilidades.grid;
                break;
        }
        construirRecycler();
    }

    private void construirRecycler() {
        listPelis = new ArrayList<PelisVO>();
        recyclerPelis = (RecyclerView) findViewById(R.id.RecyclerId);

        if(Utilidades.visualizacion==Utilidades.lista){
            recyclerPelis.setLayoutManager(new LinearLayoutManager(this));
        }else{
            recyclerPelis.setLayoutManager(new GridLayoutManager(this,3));
        }
        llenarPelis();

        AdaptadorPelis adapter = new AdaptadorPelis(listPelis);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        getApplicationContext(),listPelis.get(
                                recyclerPelis.getChildAdapterPosition(v)).getTitulo(),Toast.LENGTH_SHORT).show();
            }
        });
        recyclerPelis.setAdapter(adapter);
    }
}
