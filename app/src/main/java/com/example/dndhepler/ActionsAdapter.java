package com.example.dndhepler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class ActionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final ArrayList<CategoryActions> list;
    public ActionsAdapter(ArrayList<CategoryActions> list) {
        this.list =list;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == CategoryActions.TYPE_CATEGORY) {
            View view = inflater.inflate(R.layout.item_category_actions, parent, false);
            return  new CategoryViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.item_actions, parent, false);
            return  new ActionsViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CategoryActions item = list.get(position);
        if (holder instanceof CategoryViewHolder) {
            CategoryViewHolder cViewHolder = (CategoryViewHolder) holder;
            cViewHolder.categoryName.setText(item.getName());
            if (item.getDescription() != null && !item.getDescription().isEmpty()) {
                cViewHolder.categoryDescription.setText(item.getDescription());
            } else {
                cViewHolder.categoryDescription.setText("Описания нет");
            }
        } else if (holder instanceof ActionsViewHolder) {
            Actions actions = item.getActions();
            ActionsViewHolder viewHolder = (ActionsViewHolder) holder;
            viewHolder.actionsName.setText(actions.getName());
            viewHolder.actionsInfo.setText(actions.getDescription());
        }
    }

    @Override
    public  int getItemCount() {
        return list.size();
    }

    public  static  class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        TextView categoryDescription;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.categoryActionsName);
            categoryDescription = itemView.findViewById(R.id.categoryActionsDescription);
        }
    }

    public  static  class  ActionsViewHolder extends  RecyclerView.ViewHolder {
        TextView actionsName;
        TextView actionsInfo;
        public ActionsViewHolder(@NonNull View itemView) {
            super(itemView);
            actionsName = itemView.findViewById(R.id.actionsName);
            actionsInfo = itemView.findViewById(R.id.actionsInfo);
        }
    }
}
