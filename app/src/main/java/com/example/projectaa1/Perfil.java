package com.example.projectaa1;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.example.projectaa1.io.ApiInterface;
import com.example.projectaa1.io.response.usuario;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Perfil extends AppCompatActivity {

    private TextView nombreTextView, emailTextView, passwordTextView, tarjetaTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // Inicializa las vistas
        nombreTextView = findViewById(R.id.nombre);
        emailTextView = findViewById(R.id.email);
        passwordTextView = findViewById(R.id.password);
        tarjetaTextView = findViewById(R.id.Tarjeta);

        // Recupera el email del usuario almacenado en SharedPreferences
        String userEmail = obtenerUserEmailDeSharedPreferences();

        // Realiza la solicitud al servidor para obtener los datos del perfil
        obtenerDatosPerfil(userEmail);
    }

    private void obtenerDatosPerfil(String userEmail) {
        // Llama a tu API para obtener los datos del perfil del usuario con el email proporcionado
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.39:9000/")  // Ajusta la URL base según tu configuración
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<usuario> call = apiInterface.obtenerDatosPerfilPorEmail(userEmail);

        call.enqueue(new Callback<usuario>() {
            @Override
            public void onResponse(Call<usuario> call, Response<usuario> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Muestra los datos en la interfaz de usuario
                    usuario perfilUsuario = response.body();
                    mostrarDatosPerfil(perfilUsuario);
                } else {
                    mostrarError(response);
                }
            }

            @Override
            public void onFailure(Call<usuario> call, Throwable t) {
                mostrarError(t);
            }
        });
    }

    private void mostrarDatosPerfil(usuario perfilUsuario) {
        // Muestra los datos en los TextView correspondientes
        nombreTextView.setText("Nombre: " + (perfilUsuario.getNombre() != null ? perfilUsuario.getNombre() : "N/A"));
        emailTextView.setText("Email: " + (perfilUsuario.getEmail() != null ? perfilUsuario.getEmail() : "N/A"));
        passwordTextView.setText("Contraseña: " + (perfilUsuario.getPassword() != null ? perfilUsuario.getPassword() : "N/A"));
        tarjetaTextView.setText("Tarjeta de crédito: " +  perfilUsuario.getTarj() );
    }

    private void mostrarError(Response<?> response) {
        String error;
        if (response.errorBody() != null) {
            try {
                error = response.errorBody().string();
            } catch (IOException e) {
                error = "Error desconocido al leer el cuerpo del error";
                e.printStackTrace();
            }
        } else {
            error = "Error desconocido: " + response.message();
        }

        Toast.makeText(Perfil.this, "Error al obtener datos del perfil: " + error, Toast.LENGTH_SHORT).show();
    }

    private void mostrarError(Throwable t) {
        String error = t.getMessage();
        Toast.makeText(Perfil.this, "Error de conexión: " + error, Toast.LENGTH_SHORT).show();
    }

    // Método para obtener el email del usuario almacenado en SharedPreferences
    private String obtenerUserEmailDeSharedPreferences() {
        SharedPreferences preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        return preferences.getString("user_email", "");
    }
}
