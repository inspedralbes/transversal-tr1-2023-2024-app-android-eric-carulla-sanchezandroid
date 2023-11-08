package com.example.projectaa1.io;


import com.example.projectaa1.io.response.Items;

import com.example.projectaa1.io.response.usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/Items")
    Call<List<Items>> obtenerItems();

    @GET("/usuario")
    Call<List<usuario>> obtenerUsuario();



}
