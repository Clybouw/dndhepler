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

// Адаптер для RecyclerView отвечает за отображение списка монстров
public class BestiaryAdapter extends RecyclerView.Adapter<BestiaryAdapter.ViewHolder> {
    // Контекст Activity
    Context contextMonster;
    // Список монстров
    ArrayList<Bestiary> monsters;

    // Конструктор адаптера получает context и список монстров
    public BestiaryAdapter(Context contextMonster, ArrayList<Bestiary> monsters) {
        this.contextMonster = contextMonster;
        this.monsters = monsters;
    }

    // Создание нового элемента списка
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            // Родительский контейнер RecyclerView
            @NonNull ViewGroup parent, int viewType) {
        // Загружаем стандартный layout с двумя TextView
        View view = LayoutInflater.from(contextMonster).inflate(android.R.layout.simple_list_item_2, parent, false);
        // Возвращаем новый ViewHolder
        return new ViewHolder(view);
    }

    // Заполнение элемента списка данными
    @Override
    public void onBindViewHolder(
            // ViewHolder текущего элемента
            @NonNull ViewHolder holder, int position) {
        //Цвет текста в теории
        // Получаем монстра по позиции
        Bestiary monster = monsters.get(position);
        // Устанавливаем опасность в первый TextView
        holder.danger.setText(monster.danger);
        // Устанавливаем имя во второй TextView
        holder.name.setText(monster.name);
        // Обработка нажатия на элемент списка
        holder.itemView.setOnClickListener(v -> {
            // Создаем Intent для открытия MonsterActivity
            Intent intent = new Intent(contextMonster, MonsterActivity.class);
            // Передаем данные монстра в новую Activity
            intent.putExtra("name", monster.name);
            intent.putExtra("danger", monster.danger);
            intent.putExtra("type", monster.type);
            intent.putExtra("ac", monster.ac);
            intent.putExtra("initiative", monster.initiative);
            intent.putExtra("hp", monster.hp);
            intent.putExtra("move", monster.move);
            intent.putExtra("abilities", monster.abilities);
            intent.putExtra("skills", monster.skills);
            intent.putExtra("resists", monster.resists);
            intent.putExtra("immuns", monster.immuns);
            intent.putExtra("feel", monster.feel);
            intent.putExtra("languages", monster.languages);
            intent.putExtra("area", monster.area);
            intent.putExtra("exp", monster.exp);
            intent.putExtra("features", monster.features);
            intent.putExtra("actions", monster.actions);
            intent.putExtra("legendaryActions", monster.legendaryActions);
            intent.putExtra("bonusActions", monster.bonusActions);
            intent.putExtra("reactions", monster.reactions);
            // Открываем MonsterActivity
            contextMonster.startActivity(intent);
        });
    }

    // Возвращает количество элементов списка
    @Override
    public int getItemCount() {
        return monsters.size();
    }
    // ViewHolder хранит ссылки на View внутри элемента списка
    static class ViewHolder extends RecyclerView.ViewHolder {
        // Поле имени монстра
        TextView name;
        // Поле опасности монстра
        TextView danger;
        // Конструктор ViewHolder
        public ViewHolder(@NonNull View itemView) {
            // Передаем itemView в родительский класс
            super(itemView);
            // Находим первый TextView
            danger = itemView.findViewById(android.R.id.text1);
            // Находим второй TextView
            name = itemView.findViewById(android.R.id.text2);
        }
    }
}