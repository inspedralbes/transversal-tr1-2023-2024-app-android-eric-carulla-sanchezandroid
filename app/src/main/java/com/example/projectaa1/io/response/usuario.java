package com.example.projectaa1.io.response;



public class usuario {
    private String nombre;
    private String email;
    private String password;
    private int tarj;

    public usuario(String nombre, String email, String password, int tarj) {
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

    public int getTarj() {
        return tarj;
    }

    public void setTarj(int tarj) {
        this.tarj = tarj;
    }

}
