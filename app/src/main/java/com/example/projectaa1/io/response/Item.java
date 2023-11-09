package com.example.projectaa1.io.response;

import android.provider.ContactsContract;

public class Item {
    private int id;

    public String getImagen() {
        return Imagen;
    }

    private String nombre;
    private String Imagen;
    // Constructor con parámetros
    public Item(int id, String nombre, String descripcion, double precio,String Imagen) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.Imagen = Imagen;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    private String descripcion;
    private double precio;

}


