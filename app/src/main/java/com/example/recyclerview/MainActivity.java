package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<PelisVO> listPelis;
    RecyclerView recyclerPelis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listPelis = new ArrayList<PelisVO>();
        recyclerPelis = (RecyclerView) findViewById(R.id.RecyclerId);
        recyclerPelis.setLayoutManager(new LinearLayoutManager(this));
        
        llenarPelis();
        //recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        //recycler.setLayoutManager(new GridLayoutManager(this,2));



        AdaptadorPelis adapter = new AdaptadorPelis(listPelis);
        recyclerPelis.setAdapter(adapter);
    }

    private void llenarPelis() {
        listPelis.add(new PelisVO("El Pianista",getResources().getString(R.string.elpianista_descrip),R.drawable.elpianista));
        listPelis.add(new PelisVO("Avatar",getResources().getString(R.string.avatar_descrip),R.drawable.avatar));
        listPelis.add(new PelisVO("Forrest Gump",getResources().getString(R.string.forrestgump_descrip),R.drawable.forrestgump));
        listPelis.add(new PelisVO("Gladiator",getResources().getString(R.string.gladiator_descrip),R.drawable.gladiator));
        listPelis.add(new PelisVO("La Lista de Schindler",getResources().getString(R.string.lalistadeschindler_descrip),R.drawable.lalistadeschindler));
        listPelis.add(new PelisVO("Mejor Imposible",getResources().getString(R.string.mejorimposible_descrip),R.drawable.mejorimposible));
    }
}
