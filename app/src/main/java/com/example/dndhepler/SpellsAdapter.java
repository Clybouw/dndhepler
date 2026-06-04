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
public class SpellsAdapter extends RecyclerView.Adapter<SpellsAdapter.ViewHolder> {
    Context contextSpell;
    ArrayList<Spells> spells;

    public SpellsAdapter(Context contextSpells, ArrayList<Spells> spells) {
    this.contextSpell = contextSpells;
    this.spells = spells;
}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(contextSpell).inflate(android.R.layout.simple_list_item_2, parent, false);
    return new ViewHolder(view);
}

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Spells spell = spells.get(position);
        holder.level.setText(spell.level);
        holder.name.setText(spell.name);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(contextSpell, SorceryActivity.class);
            intent.putExtra("name", spell.name);
            intent.putExtra("level", spell.level);
            intent.putExtra("casttime", spell.casttime);
            intent.putExtra("range", spell.range);
            intent.putExtra("components", spell.components);
            intent.putExtra("duraction", spell.duraction);
            intent.putExtra("classes", spell.classes);
            intent.putExtra("subclasses", spell.subclasses);
            intent.putExtra("description", spell.description);
            intent.putExtra("lvlup", spell.lvlup);
            contextSpell.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return spells.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView level;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            level = itemView.findViewById(android.R.id.text1);
            name = itemView.findViewById(android.R.id.text2);
        }
    }
}
