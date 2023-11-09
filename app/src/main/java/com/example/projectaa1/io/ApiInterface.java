package com.example.projectaa1.io;


import com.example.projectaa1.io.response.Items;

import com.example.projectaa1.io.response.usuario;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import com.example.projectaa1.io.response.usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

        @GET("/Items")
        Call<List<Items>> obtenerItems();

        @GET("/usuario")
        Call<List<usuario>> obtenerUsuario();

        @POST("/usuario")
        Call<Void> registrarUsuario(@Body HashMap<String, String> usuario);

        @POST("/login")
        Call<Void> Login(@Body HashMap<String, String> usuario);

        @GET("perfil/{email}")
        Call<usuario> obtenerDatosPerfilPorEmail(@Path("email") String email);




}
