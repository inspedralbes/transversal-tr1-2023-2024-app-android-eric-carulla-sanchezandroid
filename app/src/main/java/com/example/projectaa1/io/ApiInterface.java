package com.example.projectaa1.io;


import com.example.projectaa1.io.response.Items;

import com.example.projectaa1.io.response.usuario;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import com.example.projectaa1.io.response.usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
public interface ApiInterface {

        @GET("/Items")
        Call<List<Items>> obtenerItems();

        @GET("/usuario")
        Call<List<usuario>> obtenerUsuario();

        @POST("/usuario")
        Call<Void> registrarUsuario(@Body HashMap<String, String> usuario);
    }
