package com.example.recyclerview;

public class PelisVO {
    private String titulo;
    private String descripcionBreve;
    private int imagen;

    public PelisVO(String titulo, String descripcionBreve, int imagen) {
        this.titulo = titulo;
        this.descripcionBreve = descripcionBreve;
        this.imagen = imagen;
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

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

}
