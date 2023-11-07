package com.example.projectaa1.io;

import com.example.projectaa1.io.response.Item;
import com.example.projectaa1.io.response.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/Items")
    Call<List<Item>> obtenerItems();

    @GET("/usuario")
    Call<List<Usuario>> obtenerUsuario();



}
