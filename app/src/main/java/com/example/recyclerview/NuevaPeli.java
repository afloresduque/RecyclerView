package com.example.recyclerview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NuevaPeli extends AppCompatActivity implements Serializable {

    ImageView image;
    EditText etTitulo, etGenero, etDireccion, etDescripcionBreve, etDescripcion;
    Switch sfavorita;
    private int PICK_IMAGE = 300;
    Uri uri = Uri.parse("");
    Spinner spinnerGenero;
    ArrayAdapter<String> spiAdapter;
    List<String> generos = Arrays.asList("Género:","Drama","Ciencia-ficcion","Comedia","Historica","Terror","Suspense","Accion","Aventura");


    protected void onCreate(Bundle saveInstanceState){
        //Log.e("onOptionesItemsSelected", "entra o no?");
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_nueva_peli);

        image = findViewById(R.id.imageViewAdd);
        etTitulo = findViewById(R.id.etTitulo);
        //etGenero = findViewById(R.id.etGenero);
        etDireccion = findViewById(R.id.etDireccion);
        etDescripcionBreve = findViewById(R.id.etDescripcionBreve);
        etDescripcion = findViewById(R.id.etDescripcion);
        sfavorita = findViewById(R.id.favoritaSwitch);
        spinnerGenero = findViewById(R.id.spiGenero);

        spiAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, generos);
        spinnerGenero.setAdapter(spiAdapter);

        personalizarTituloBarra();

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirGaleria();
            }
        });
    }

    private void abrirGaleria() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK){
                uri = data.getData();
                image.setImageURI(uri);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_nueva_peli, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intentDevolver = new Intent();
        Bundle bundleDevolver = new Bundle();

        int id = item.getItemId();
        String genero = spinnerGenero.getSelectedItem().toString();
        Log.e("genero", String.valueOf(uri));

        switch (id) {
            case R.id.aceptarCrearPeli:
                /*IMPOSIBLE pasarle la imagen de vuelva al Main
                Lo he intentado pasando la uri, pasando String, pasando drawable, pasando bitmap..
                no llega a recibir la imagen o da error cuando se pasa por parámetro al intent

                Consigo que en la pantalla de nueva peli obtenga la imagen de la memoria externa,
                sin embargo, la imagen no llega a almacenarse con el nuevo objeto.
                 */
                bundleDevolver.putSerializable("nuevaPeli", new PelisVO(etTitulo.getText().toString(),
                        etDescripcionBreve.getText().toString(), etDescripcion.getText().toString(),
                        uri.toString(), genero,etDireccion.getText().toString(),
                        sfavorita.isChecked()));

                //Bitmap bitmap =((BitmapDrawable) image.getDrawable()).getBitmap();
                intentDevolver.putExtras(bundleDevolver);
                //String uriS = uri.toString();
                //intentDevolver.putExtra("uri", uriS);
                //intentDevolver.putExtra("imagen", bitmap);


                Log.e("nuevapeli", String.valueOf(uri));

                setResult(Activity.RESULT_OK, intentDevolver);
                finish();
                break;
            case R.id.cancelarCrearPeli:
                setResult(Activity.RESULT_CANCELED);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void personalizarTituloBarra() {
        ActionBar barra = getSupportActionBar();
        barra.setTitle("Datos de la nueva peli");
    }

}
