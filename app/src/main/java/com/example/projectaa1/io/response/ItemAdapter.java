package com.example.projectaa1.io.response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectaa1.R;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<Item> itemList;

    public ItemAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.prodName.setText(item.getNombre());
        holder.prodPrice.setText("Precio: " + item.getPrecio());
        holder.prodDescription.setText(item.getDescripcion());

        // Carga la imagen del producto aquí usando una biblioteca como Picasso o Glide.
        // Por ejemplo: Picasso.get().load(item.getImageUrl()).into(holder.prodImage);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView prodName, prodPrice, prodDescription;

        public ItemViewHolder(View itemView) {
            super(itemView);

            prodName = itemView.findViewById(R.id.prodName);
            prodPrice = itemView.findViewById(R.id.prodPrice);
            prodDescription = itemView.findViewById(R.id.prodDescription);
        }
    }
}
