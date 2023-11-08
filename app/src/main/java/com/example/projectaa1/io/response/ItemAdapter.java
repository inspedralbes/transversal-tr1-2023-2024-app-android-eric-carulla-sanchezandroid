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

    private List<Items> itemList;

    public ItemAdapter(List<Items> itemList) {
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
        Items item = itemList.get(position);
        holder.prodName.setText(item.getNombre());
        holder.prodPrice.setText("Precio: " + item.getPrecio());
        holder.prodDescription.setText(item.getDescripcion());
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
