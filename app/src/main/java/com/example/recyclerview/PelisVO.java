package com.example.recyclerview;


import java.io.Serializable;

public class PelisVO implements Serializable {
    private String titulo;
    private String descripcion;
    private String descripcionBreve;
    private int imagen=0;
    private String imagenU;
    private String genero;
    private String direccion;
    private boolean favorito;



    public PelisVO(String titulo, String descripcionBreve, String descripcion, int imagen, String genero, String direccion) {
        this.titulo = titulo;
        this.descripcionBreve = descripcionBreve;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.genero = genero;
        this.direccion = direccion;
    }

    public PelisVO(String titulo, String descripcionBreve, String descripcion, int imagen, String genero, String direccion, Boolean favorito) {
        this.titulo = titulo;
        this.descripcionBreve = descripcionBreve;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.genero = genero;
        this.direccion = direccion;
        this.favorito = favorito;
    }

    public PelisVO(String titulo, String descripcionBreve, String descripcion, String imagen, String genero, String direccion, Boolean favorito) {
        this.titulo = titulo;
        this.descripcionBreve = descripcionBreve;
        this.descripcion = descripcion;
        this.imagenU = imagen;
        this.genero = genero;
        this.direccion = direccion;
        this.favorito = favorito;
    }

    public PelisVO(PelisVO pelisVO) {
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

    public boolean isFavorito() {        return favorito;    }

    public void setFavorito(boolean favorito) {        this.favorito = favorito;    }

    public String getDireccion() {        return direccion;    }

    public void setDireccion(String direccion) {        this.direccion = direccion;    }
    public String getImagenU() {
        return imagenU;
    }

    public void setImagenU(String imagenU) {
        this.imagenU = imagenU;
    }

}
