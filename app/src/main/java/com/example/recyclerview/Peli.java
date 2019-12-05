package com.example.recyclerview;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Peli extends AppCompatActivity {

    ImageView ivImagen;
    TextView tvTitulo;
    TextView tvDescripcion;
    TextView tvGenero;
    TextView tvDireccion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peli);

        ArrayList<PelisVO> listaPelis;

        listaPelis = (ArrayList<PelisVO>) getIntent().getSerializableExtra("lista");
        int peliculaAbierta =Integer.parseInt(getIntent().getSerializableExtra("Peli").toString());
        PelisVO peliAbierta = new PelisVO(listaPelis.get(peliculaAbierta));


        ivImagen = (ImageView) findViewById(R.id.idImagen);
        tvTitulo = (TextView) findViewById(R.id.idTitulo);
        tvDescripcion = (TextView) findViewById(R.id.idDescripcion);
        tvGenero = (TextView) findViewById(R.id.idGenero);
        tvDireccion = (TextView) findViewById(R.id.idDireccion);

        tvTitulo.setText(peliAbierta.getTitulo());
        tvDescripcion.setText(peliAbierta.getDescripcion());
        ivImagen.setImageResource(peliAbierta.getImagen());
        tvGenero.setText("Género: " + peliAbierta.getGenero());
        tvDireccion.setText("Dirección: " + peliAbierta.getDireccion());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tituloActionBar(peliAbierta.getTitulo());
    }

    private void tituloActionBar(String titulo) {
        ActionBar barraSuperior = getSupportActionBar();
        barraSuperior.setTitle(titulo);
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_activity_peli, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem peli) {

        int id = peli.getItemId();

        switch (id) {

            case R.id.marcarFavorito:
               /* if (!setaRecibida.getFavorito()) {
                    GestorFavoritos.anadirAFavoritos(setaRecibida, getApplicationContext());
                } else if (setaRecibida.getFavorito()){
                    GestorFavoritos.eliminarDeFavoritos(setaRecibida, getApplicationContext());
                }*/
                return true;

            case R.id.compartir:
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                String mensaje= "Te recomiendo esta peli:\n\n" + tvTitulo.getText().toString().toUpperCase()+ "\n\n" + tvDescripcion.getText().toString();
                sendIntent.putExtra(Intent.EXTRA_TEXT, mensaje);
                Intent shareIntent = Intent.createChooser(sendIntent, "Info de la película: " + peli.getTitle().toString());
                startActivity(shareIntent);
                return true;

            case R.id.verWeb:

                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://es.wikipedia.org/wiki/" + tvTitulo.getText()));
                startActivity(webIntent);
                return true;

            case R.id.enviarCorreo:
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:"));
                //ASunto del mensaje
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Te recomiendo esta peli: " + tvTitulo.getText().toString());
                //Descripcion del mensaje
                emailIntent.putExtra(Intent.EXTRA_TEXT, tvDescripcion.getText());
                startActivity(emailIntent);

                return true;
            default:
                    return super.onOptionsItemSelected(peli);
        }


    }
}
