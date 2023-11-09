package com.example.projectaa1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
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

// Dentro de tu clase MainActivity
public class MainActivity extends AppCompatActivity {

    private final String BASE_URL = "http://192.168.1.39:9000/login/";  // Reemplaza con tu URL base
    private EditText etEmail, etPassword;
    private String email, password;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void login(View view) {

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);


        // Obtén las credenciales del usuario
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();

        HashMap<String,String> userData = new HashMap<>();
        userData.put("email",email);
        userData.put("password",password);

        // Crea una instancia de Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crea una instancia de la interfaz de la API
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);


        Call<Void> call = apiInterface.Login(userData);
        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                // Maneja la respuesta del servidor
                if (response.isSuccessful() ) {
                    // El inicio de sesión fue exitoso
                    Toast.makeText(MainActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

                   Intent i = new Intent(MainActivity.this, MenuApp.class);
                   startActivity(i);

                } else {
                    // El inicio de sesión falló

                    Toast.makeText(MainActivity.this, "Credenciales incorrectas "  , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                String error = t.getMessage();
                // Maneja errores de la solicitud
                Toast.makeText(MainActivity.this, "Error de conexión" + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void register(View view) {

        Intent i = new Intent(this, Registrar.class);
        startActivity(i);


    }

}