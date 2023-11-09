package com.example.projectaa1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.projectaa1.io.ApiInterface;
import com.example.projectaa1.io.response.usuario;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Registrar extends AppCompatActivity {
    private EditText editTextNombre;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextTarjeta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextTarjeta = findViewById(R.id.editTextTarjeta);
    }
    public void registrarUsuario(View view) {
        String nombre = editTextNombre.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        int tarj = Integer.parseInt(editTextTarjeta.getText().toString());

        // Crear un HashMap para almacenar los datos del usuario
        HashMap<String, String> userData = new HashMap<>();
        userData.put("nombre", nombre);
        userData.put("email", email);
        userData.put("password", password);
        userData.put("tarj", String.valueOf(tarj)); // Convierte a String

        // Crear una instancia de Retrofit y la interfaz de la API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.39:9000/")  // Reemplaza con la dirección de tu servidor
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        // Realizar la solicitud para registrar el usuario
        Call<Void> call = apiInterface.registrarUsuario(userData);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(Registrar.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Registrar.this, MainActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(Registrar.this, "Error al registrar usuario: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                String error = t.getMessage();
                Toast.makeText(Registrar.this, "Error de conexión: " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }}
