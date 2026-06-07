package com.example.dndhepler;

import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class PropertyWeaponsAdapter extends RecyclerView.Adapter<PropertyWeaponsAdapter.ViewHolder> {

    private final ArrayList<PropertyWeapons> list;

    public PropertyWeaponsAdapter(ArrayList<PropertyWeapons> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_property_weapons, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PropertyWeapons item = list.get(position);
        setBoldText(holder.propertyWeaponsText, item.getName(), item.getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView propertyWeaponsText;
         public ViewHolder(@NonNull View itemView) {
             super(itemView);
             propertyWeaponsText = itemView.findViewById(R.id.propertyWeaponsText);
        }
    }

    private void setBoldText(TextView textView, String name, String description) {
        String nameList = name + ". ";
        String fullText = nameList + description;
        SpannableString spannable = new SpannableString(fullText);
        spannable.setSpan(new StyleSpan(Typeface.BOLD), 0, nameList.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannable);
    }
}
