package com.example.projectaa1.io.response;



public class usuario {
    private String nombre;
    private String email;
    private String password;
    private String tarjeta;


    public usuario(String nombre, String email, String password, String tarjeta) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.tarjeta = tarjeta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

}
