package com.example.recyclerview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PantallaFiltrar extends AppCompatActivity {

    CheckBox checkDrama,checkCiencia,checkComedia,checkHistorica,checkTerror,checkSuspense,checkAccion,checkAventuras;

    ArrayList<String> generosFiltrados = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtrar);

        checkDrama = (CheckBox) findViewById(R.id.cbDrama);
        checkCiencia = (CheckBox) findViewById(R.id.cbCiencia);
        checkComedia = (CheckBox) findViewById(R.id.cbComedia);
        checkHistorica = (CheckBox) findViewById(R.id.cbHistorica);
        checkTerror = (CheckBox) findViewById(R.id.cbTerror);
        checkSuspense = (CheckBox) findViewById(R.id.cbSuspense);
        checkAccion = (CheckBox) findViewById(R.id.cbAccion);
        checkAventuras = (CheckBox) findViewById(R.id.cbAventuras);
    }
    public void btValidar_onClick(View v){

        Intent intentGeneros = new Intent();

        validar();

        intentGeneros.putExtra("generos",generosFiltrados);
        setResult(Activity.RESULT_OK, intentGeneros);
        //Toast.makeText(this, generosFiltrados.get(0), Toast.LENGTH_LONG).show();
        finish();

    }

    private void validar(){
        if(checkDrama.isChecked()){
            generosFiltrados.add("Drama");
        }
        if(checkCiencia.isChecked()){
            generosFiltrados.add("Ciencia-ficción");
        }
        if(checkComedia.isChecked()){
            generosFiltrados.add("Comedia");
        }
        if(checkHistorica.isChecked()){
            generosFiltrados.add("Histórica");
        }
        if(checkTerror.isChecked()){
            generosFiltrados.add("Terror");
        }
        if(checkSuspense.isChecked()){
            generosFiltrados.add("Suspense");
        }
        if(checkAccion.isChecked()){
            generosFiltrados.add("Acción");
        }
        if(checkAventuras.isChecked()){
            generosFiltrados.add("Aventuras");
        }

    }
}
