package com.example.projectaa1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectaa1.io.ApiInterface;
import com.example.projectaa1.io.response.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Perfil extends AppCompatActivity {
    private TextView textViewNombre;
    private TextView textViewEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        textViewNombre = findViewById(R.id.nombre);
        textViewEmail = findViewById(R.id.email);

        // Crear una instancia de Retrofit y la interfaz de la API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.39:9000")  // Reemplaza con la dirección de tu servidor
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        // Realizar una solicitud para obtener la lista de usuarios
        Call<List<Usuario>> call = apiInterface.obtenerUsuario();
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if (response.isSuccessful()) {
                    List<Usuario> usuarios = response.body();

                    // Procesa y muestra los usuarios en la interfaz de usuario
                    if (!usuarios.isEmpty()) {
                        Usuario primerUsuario = usuarios.get(0);
                        String nombre = primerUsuario.getNombre();
                        String email = primerUsuario.getEmail();

                        // Actualiza los TextViews en la interfaz con los datos del primer usuario
                        textViewNombre.setText("Nombre: " + nombre);
                        textViewEmail.setText("Email: " + email);
                    } else {
                        // Manejar la lista de usuarios vacía
                        Toast.makeText(Perfil.this, "No se encontraron usuarios"+ response.message(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Manejar errores de respuesta, como mostrar un mensaje de error
                    Toast.makeText(Perfil.this, "Error al obtener usuarios: " + response.message(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<Usuario>>call, Throwable t) {
                String error = t.getMessage();
                // Manejar errores de red, como mostrar un mensaje de error de conexión
                Toast.makeText(Perfil.this, "Error de conexión "+ error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
