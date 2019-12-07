package com.example.recyclerview;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorPelis
        extends RecyclerView.Adapter<AdaptadorPelis.ViewHolderPelis>
        implements View.OnClickListener{
    ArrayList<PelisVO> listaPelis;
    private View.OnClickListener listener;

    public AdaptadorPelis(ArrayList<PelisVO> listaPelis) {
        this.listaPelis = listaPelis;
    }


    @NonNull
    @Override
    public ViewHolderPelis onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        int layout = 0;
        if(Utilidades.visualizacion==Utilidades.lista){
            layout = R.layout.item_list_pelis;
        }else {
            layout = R.layout.item_grid_pelis;
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(layout,null,false);
        view.setOnClickListener(this);
        return new ViewHolderPelis(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPelis holder, int position) {
        holder.etiTitulo.setText(listaPelis.get(position).getTitulo());
        if(Utilidades.visualizacion==Utilidades.lista){
            holder.etiDescripcionBreve.setText(listaPelis.get(position).getDescripcionBreve());
        }

        if(listaPelis.get(position).getImagen()>0) {
            holder.etiImagen.setImageResource(listaPelis.get(position).getImagen());
            Log.e("adaptadorpelisnormales", String.valueOf(listaPelis.get(position).getImagen()));
        }else {

           Picasso.get()
                    .load(Uri.parse(listaPelis.get(position).getImagenU()))
                    .resize(10,10)
                   .centerCrop()
                    .error(R.drawable.rollopeli)
                    .into(holder.etiImagen);
           Log.e("adaptador", listaPelis.get(position).getImagenU());
            //holder.etiImagen.setImageURI(Uri.parse(listaPelis.get(position).getImagenU()));
        }




    }

    @Override
    public int getItemCount() {
        return listaPelis.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }


    public class ViewHolderPelis extends RecyclerView.ViewHolder{

        TextView etiTitulo, etiDescripcionBreve;
        ImageView etiImagen;

        @SuppressLint("WrongViewCast")
        public ViewHolderPelis(View itemView){
            super(itemView);
            etiTitulo = (TextView) itemView.findViewById(R.id.idTitulo);
            if(Utilidades.visualizacion==Utilidades.lista){
                etiDescripcionBreve = (TextView) itemView.findViewById(R.id.idDescripcion);
            }
            etiImagen = (ImageView) itemView.findViewById(R.id.idImagen);
        }

    }

    public ArrayList<PelisVO> getListaPelis() {
        return listaPelis;
    }

    public void setListaPelis(ArrayList<PelisVO> listaPelis) {
        this.listaPelis = listaPelis;
    }
}
