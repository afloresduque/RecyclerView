package com.example.recyclerview;

import java.io.Serializable;

public class PelisVO implements Serializable {
    private String titulo;
    private String descripcion;
    private String descripcionBreve;
    private int imagen;
    private String genero;

    public PelisVO(String titulo, String descripcionBreve, String descripcion, int imagen, String genero) {
        this.titulo = titulo;
        this.descripcionBreve = descripcionBreve;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.genero = genero;
    }

    public PelisVO() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcionBreve() {
        return descripcionBreve;
    }

    public void setDescripcionBreve(String descripcionBreve) {
        this.descripcionBreve = descripcionBreve;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

}
