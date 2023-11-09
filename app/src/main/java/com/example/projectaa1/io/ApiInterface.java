package com.example.projectaa1.io;


import com.example.projectaa1.io.response.Item;
import com.example.projectaa1.io.response.usuario;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface ApiInterface {

        @GET("/items")
        Call<List<Item>> obtenerItems();

        @GET("/usuario")
        Call<List<usuario>> obtenerUsuario();

        @POST("/usuario")
        Call<Void> registrarUsuario(@Body HashMap<String, String> usuario);

        @POST("/login")
        Call<Void> Login(@Body HashMap<String, String> usuario);

        @GET("perfil/{email}")
        Call<usuario> obtenerDatosPerfilPorEmail(@Path("email") String email);



}
