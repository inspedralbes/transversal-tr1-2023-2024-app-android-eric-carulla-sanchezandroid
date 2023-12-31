package com.example.projectaa1.io;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiAdapter {

        private static ApiInterface API_SERVICE;

        private static final String BASE_URL = "http://192.168.1.39:9000/";

        public static ApiInterface getApiService() {
            // Creamos un interceptor y le indicamos el log level a usar
            final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            // Asociamos el interceptor a las peticiones
            final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);

            if (API_SERVICE == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(httpClient.build()) // <-- set log level
                        .build();

                API_SERVICE = retrofit.create(ApiInterface.class);
            }

            return API_SERVICE;
        }

    }











