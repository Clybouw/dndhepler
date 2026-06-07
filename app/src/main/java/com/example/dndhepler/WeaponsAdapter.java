package com.example.dndhepler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class WeaponsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final ArrayList<CategoryWeapons> list;
    public WeaponsAdapter(ArrayList<CategoryWeapons> list) {
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == CategoryWeapons.TYPE_CATEGORY) {
            View view = inflater.inflate(R.layout.item_category_weapons, parent,false);
            return new CategoryViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.item_weapons, parent, false);
            return new WeaponsViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CategoryWeapons item = list.get(position);
        if (holder instanceof CategoryViewHolder) {
            ((CategoryViewHolder) holder).categoryName.setText(item.getCategoryName());
        }
        else if (holder instanceof WeaponsViewHolder) {
            Weapons weapons = item.getWeapons();
            WeaponsViewHolder viewHolder = (WeaponsViewHolder) holder;
            viewHolder.weaponsName.setText(weapons.getName());
            StringBuilder builder = new StringBuilder();
            if (!weapons.getDescription().isEmpty()) {
                builder.append(weapons.getDescription()).append("\n");
            }
            builder.append("Цена: ").append(weapons.getPrice()).append("\n");
            builder.append("Урон: ").append(weapons.getDamage()).append("\n");
            builder.append("Вес: ").append(weapons.getWeight()).append("\n");
            builder.append("Свойства: ").append(weapons.getProperty());
            viewHolder.weaponsInfo.setText(builder.toString());
            viewHolder.weaponsName.setOnClickListener(v -> {
                if (viewHolder.weaponsLayout.getVisibility() == View.GONE) {
                    viewHolder.weaponsLayout.setVisibility(View.VISIBLE);
                } else {
                    viewHolder.weaponsLayout.setVisibility(View.GONE);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.categoryWeaponsName);
        }
    }

    public static class WeaponsViewHolder extends RecyclerView.ViewHolder {
        TextView weaponsName;
        LinearLayout weaponsLayout;
        TextView weaponsInfo;
        public WeaponsViewHolder(@NonNull View itemView) {
            super(itemView);
            weaponsName = itemView.findViewById(R.id.weaponsName);
            weaponsLayout = itemView.findViewById(R.id.weaponsLayout);
            weaponsInfo = itemView.findViewById(R.id.weaponsInfo);
        }
    }
}
