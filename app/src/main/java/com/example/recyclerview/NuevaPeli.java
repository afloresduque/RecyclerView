package com.example.recyclerview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class NuevaPeli extends AppCompatActivity {

    ImageView image;
    EditText etTitulo, etGenero, etDireccion, etDescripcionBreve, etDescripcion;
    Switch sfavorita;
    private int PICK_IMAGE = 300;

    protected void onCreate(Bundle saveInstanceState){
        Log.e("onOptionesItemsSelected", "entra o no?");
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_nueva_peli);

        image = findViewById(R.id.imageViewAdd);
        etTitulo = findViewById(R.id.etTitulo);
        etGenero = findViewById(R.id.etGenero);
        etDireccion = findViewById(R.id.etDireccion);
        etDescripcionBreve = findViewById(R.id.etDescripcionBreve);
        etDescripcion = findViewById(R.id.etDescripcion);
        sfavorita = findViewById(R.id.favoritaSwitch);

        personalizarTituloBarra();

        image = (ImageView)findViewById(R.id.idImagen);

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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK){

            if (data != null) {
                Uri uri = data.getData();
                image.setImageURI(uri);
            }
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

        switch (id) {
            case R.id.aceptarCrearSeta:
                //La imagen realmente no se pone, da igual qué selecciones ya que no he conseguido
                //poder realmente pasarle la imagen a mi objeto.
                bundleDevolver.putSerializable("nuevaPeli", new PelisVO(etTitulo.getText().toString(),
                        etDescripcionBreve.getText().toString(), etDescripcion.getText().toString(),
                        R.drawable.rollopeli, etGenero.getText().toString(),etDireccion.getText().toString(),
                        sfavorita.isChecked()));
                intentDevolver.putExtras(bundleDevolver);
                setResult(Activity.RESULT_OK, intentDevolver);
                finish();
                break;
            case R.id.cancelarCrearSeta:
                setResult(Activity.RESULT_CANCELED);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void personalizarTituloBarra() {
        ActionBar barra = getSupportActionBar();
        barra.setTitle(etTitulo.toString());
    }

}
