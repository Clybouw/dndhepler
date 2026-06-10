package com.example.dndhepler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {
    Context contextItems;
    ArrayList<Items> items;
    public ItemsAdapter(Context contextItems, ArrayList<Items> items) {
        this.contextItems = contextItems;
        this.items = items;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(contextItems).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Items item = items.get(position);
        holder.name.setText(item.name);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(contextItems, ItemActivity.class);
            intent.putExtra("name", item.name);
            intent.putExtra("aligment", item.aligment);
            intent.putExtra("price", item.price);
            intent.putExtra("description", item.description);
            intent.putExtra("tableDescription", item.tableDescription);
            contextItems.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(android.R.id.text1);
        }
    }
}
