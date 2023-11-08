package com.example.projectaa1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toast;

import com.example.projectaa1.io.ApiInterface;
import com.example.projectaa1.io.response.ItemAdapter;
import com.example.projectaa1.io.response.Items;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MenuApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_app);

        // Crear una instancia de Retrofit y la interfaz de la API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.39:9000/Items/")  // Agrega una barra inclinada al final
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        // Realizar una solicitud para obtener la lista de elementos
        Call<List<Items>> call = apiInterface.obtenerItems();
        call.enqueue(new Callback<List<Items>>() {
            @Override
            public void onResponse(Call<List<Items>> call, Response<List<Items>> response) {
                if (response.isSuccessful()) {
                    List<Items> items = response.body();
                    // Procesar y mostrar los elementos en la interfaz de compra
                    mostrarItemsEnInterfaz(items);
                } else {
                    // Manejar errores de respuesta, como mostrar un mensaje de error
                    Toast.makeText(MenuApp.this, "Error al obtener elementos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Items>> call, Throwable t) {
                // Manejar errores de red, como mostrar un mensaje de error de conexión
                Toast.makeText(MenuApp.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemPerfil:
                Toast.makeText(this, "Perfil", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, Perfil.class);
                startActivity(i);
                break;
            case R.id.itemSalir:
                Toast.makeText(this, "Tancar Sessió", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void mostrarItemsEnInterfaz(List<Items> Items) {

        // 2. En esta actividad, después de obtener los elementos, configura el RecyclerView:
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

// Crea un adaptador personalizado y configúralo con tus elementos
        ItemAdapter itemAdapter = new ItemAdapter(Items);

// Establece el adaptador en el RecyclerView
        recyclerView.setAdapter(itemAdapter);

// Configura el diseño del RecyclerView (por ejemplo, LinearLayoutManager)
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }
}
