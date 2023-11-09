package com.example.projectaa1;import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.projectaa1.io.ApiInterface;
import com.example.projectaa1.io.response.Item;
import com.example.projectaa1.io.response.ItemAdapter;

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
                .baseUrl("http://192.168.1.39:9000")  // Reemplaza con la dirección de tu servidor
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        // Realizar una solicitud para obtener la lista de elementos
        Call<List<Item>> call = apiInterface.obtenerItems();
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if (response.isSuccessful()) {
                    List<Item> items = response.body();
                    if (items != null) {
                        mostrarItemsEnInterfaz(items);
                    } else {
                        Toast.makeText(MenuApp.this, "La lista de elementos es nula", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MenuApp.this, "Error en la respuesta: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Toast.makeText(MenuApp.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("MenuApp", "Error de conexión", t);
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

    private void mostrarItemsEnInterfaz(List<Item> items) {

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // 3. Crea un adaptador personalizado para tus elementos y configúralo:
        ItemAdapter itemAdapter = new ItemAdapter(items, MenuApp.this);
        recyclerView.setAdapter(itemAdapter);
    }
}
