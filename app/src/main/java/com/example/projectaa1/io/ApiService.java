package com.example.projectaa1.io;

import java.util.ArrayList;

import retrofit2.http.GET;

public interface ApiService {


    @GET("/Items")
    Call<ArrayList<Items>> getItems();

}
