package com.example.recyclerview;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorPelis extends RecyclerView.Adapter<AdaptadorPelis.ViewHolderPelis> {
    ArrayList<PelisVO> listaPelis;

    public AdaptadorPelis(ArrayList<PelisVO> listaPelis) {
        this.listaPelis = listaPelis;
    }

    @NonNull
    @Override
    public ViewHolderPelis onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_pelis,null,false);
        return new ViewHolderPelis(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPelis holder, int position) {
        holder.etiTitulo.setText(listaPelis.get(position).getTitulo());
        holder.etiDescripcionBreve.setText(listaPelis.get(position).getDescripcionBreve());
        holder.etiImagen.setImageResource(listaPelis.get(position).getImagen());
    }

    @Override
    public int getItemCount() {
        return listaPelis.size();
    }

    public class ViewHolderPelis extends RecyclerView.ViewHolder{

        TextView etiTitulo, etiDescripcionBreve;
        ImageView etiImagen;

        @SuppressLint("WrongViewCast")
        public ViewHolderPelis(View itemView){
            super(itemView);
            etiTitulo = (TextView) itemView.findViewById(R.id.idTitulo);
            etiDescripcionBreve = (TextView) itemView.findViewById(R.id.idDescripcion);
            etiImagen = (ImageView) itemView.findViewById(R.id.idImagen);
        }

    }
}
