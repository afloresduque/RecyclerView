package com.example.recyclerview;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
    PelisVO peliAbierta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peli);

        ArrayList<PelisVO> listaPelis = new ArrayList<PelisVO>();
        listaPelis = (ArrayList<PelisVO>) getIntent().getSerializableExtra("lista");
        int peliculaAbierta =Integer.parseInt(getIntent().getSerializableExtra("Peli").toString());
        peliAbierta = listaPelis.get(peliculaAbierta);


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
        personalizarTituloBarra(peliAbierta.getTitulo());
       // Log.d("onCreate", peliAbierta.getTitulo());
    }

    private void personalizarTituloBarra(String titulo) {
        ActionBar barra = getSupportActionBar();
        barra.setTitle(titulo);
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
                GestionarFavoritos.cambiarFavoritos(peliAbierta, getApplicationContext());
                if(peliAbierta.isFavorito())
                    peli.setIcon(R.drawable.star);
                else peli.setIcon(R.drawable.starvacia);
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
