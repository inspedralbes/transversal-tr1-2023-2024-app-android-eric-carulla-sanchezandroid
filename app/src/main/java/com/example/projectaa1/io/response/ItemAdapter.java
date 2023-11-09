package com.example.projectaa1.io.response;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projectaa1.R;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<Item> itemList;
    private Context context;

    public ItemAdapter(List<Item> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itemList.get(position);

        holder.idTextView.setText("ID: " + item.getId());
        holder.nombreTextView.setText("Nombre: " + item.getNombre());
        holder.descripcionTextView.setText("Descripción: " + item.getDescripcion());
        holder.precioTextView.setText("Precio: " + item.getPrecio());

        // Obtiene el ID del recurso de imagen desde el nombre de la imagen
        int imagenResourceId = 0;  // Por defecto, establecer a 0 o a un recurso predeterminado

        // Verifica si el nombre de la imagen no es nulo antes de intentar obtener el recurso
        if (item.getImagen() != null) {
            imagenResourceId = context.getResources().getIdentifier(
                    item.getImagen(), "drawable", context.getPackageName());
        }

        // Establece la imagen desde el recurso de imagen
        holder.imagenImageView.setImageResource(imagenResourceId);
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView idTextView;
        public TextView nombreTextView;
        public TextView descripcionTextView;
        public TextView precioTextView;
        public ImageView imagenImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            idTextView = itemView.findViewById(R.id.idTextView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
            descripcionTextView = itemView.findViewById(R.id.descripcionTextView);
            precioTextView = itemView.findViewById(R.id.precioTextView);
            imagenImageView = itemView.findViewById(R.id.imagenImageView);
        }
    }
}