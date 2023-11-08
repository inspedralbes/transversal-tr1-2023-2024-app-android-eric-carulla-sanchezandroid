package com.example.projectaa1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;

import android.widget.Toast;

import com.example.projectaa1.io.ApiInterface;
import com.example.projectaa1.io.response.usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Registrar extends AppCompatActivity {
    private EditText editTextNombre;
    private EditText editTextPassword;
    private EditText editTextTarjeta;
    private EditText editTextEmail;


    public void registrarUsuario(View view) {
        // Obtiene los datos ingresados por el usuario
        String nombre = editTextNombre.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String tarjeta = editTextTarjeta.getText().toString();

        usuario usuario = new usuario(nombre, email, password, tarjeta);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("URL_DEL_SERVIDOR")  // Reemplaza con la URL de tu servidor
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        Call<Void> call = apiInterface.(usuario);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // El registro fue exitoso
                    Toast.makeText(Registrar.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                } else {
                    // Manejar errores de registro
                    Toast.makeText(Registrar.this, "Error en el registro", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Manejar errores de red
                Toast.makeText(RegistroActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });






