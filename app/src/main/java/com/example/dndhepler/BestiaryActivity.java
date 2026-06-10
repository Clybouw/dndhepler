package com.example.dndhepler;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Switch;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileInputStream;
import java.util.ArrayList;

public class BestiaryActivity extends BaseActivity {

    private Toolbar toolbar;
    // Полный список монстров 24 года
    ArrayList<Bestiary> monsters24 = new ArrayList<>();
    //Полный список монстров 14 года
    ArrayList<Bestiary> monsters14 = new ArrayList<>();
    // Список монстров после фильтрации поиска
    ArrayList<Bestiary> filteredMonsters = new ArrayList<>();
    //Активный список
    boolean useBestiary = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Подключение layout activity_bestiary.xml
        setContentView(R.layout.activity_bestiary);
        // Получение Toolbar из layout
        toolbar = findViewById(R.id.toolbar);
        // Установка Toolbar как ActionBar
        setSupportActionBar(toolbar);
        // Отключение стандартного названия
        getSupportActionBar().setTitle("Бестиарий");
        // Получение RecyclerView
        RecyclerView recyclerView = findViewById(R.id.bestiaryView);
        // Установка вертикального списка
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Загрузка монстров из json файла
        monsters24 = loadJson("Bestiary5e24.json");
        monsters14 = loadJson("Bestiary5e14.json");
        //Отображение списков
        filteredMonsters.clear();
        if (!monsters14.isEmpty()) {
            filteredMonsters.addAll(monsters14);
        }
        //Кнопка фильтра
        Button filterButton = findViewById(R.id.filter);
        LinearLayout filterLayout = findViewById(R.id.filterLayout);
        //Работа фильтра
        filterButton.setOnClickListener(v -> {
            if (filterLayout.getVisibility() == View.GONE) {
                filterLayout.setVisibility(View.VISIBLE);
            } else {
                filterLayout.setVisibility(View.GONE);
            }
        });
        // Создание adapter для RecyclerView
        BestiaryAdapter adapter = new BestiaryAdapter(this, filteredMonsters);
        Switch bestiarySwitch = findViewById(R.id.bestiarySwitch);
        bestiarySwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            useBestiary = isChecked;
            filteredMonsters.clear();
            if (useBestiary) {
                filteredMonsters.addAll(monsters24);
            } else {
                filteredMonsters.addAll(monsters14);
            }
            adapter.notifyDataSetChanged();
        });
        // Установка adapter
        recyclerView.setAdapter(adapter);
        // Получение SearchView
        SearchView searchView = findViewById(R.id.searchBestiaryView);
        // Слушатель поиска
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            // Нажатие кнопки поиска
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            // Изменение текста поиска
            @Override
            public boolean onQueryTextChange(String newText) {
                // Очистка списка фильтрации
                filteredMonsters.clear();
                // Проверка каждого монстра
                ArrayList<Bestiary> currentList;
                if (useBestiary) {
                    currentList = monsters24;
                } else {
                    currentList = monsters14;
                }
                for (Bestiary monster : currentList) {
                    // Если имя содержит введенный текст
                    if (monster.name.toLowerCase().contains(newText.toLowerCase())) {
                        // Добавление монстра
                        filteredMonsters.add(monster);
                    }
                }
                // Обновление RecyclerView
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    // Загрузка данных из json файла
    private ArrayList<Bestiary> loadJson(String fileName) {
        ArrayList<Bestiary> list = new ArrayList<>();
        try {
            // Открытие файла
            FileInputStream fis = openFileInput(fileName);
            // Создание массива байтов
            byte[] bytes = new byte[fis.available()];
            // Чтение файла
            fis.read(bytes);
            // Закрытие файла
            fis.close();
            // Преобразование байтов в строку
            String json = new String(bytes);
            // Преобразование строки в JSONArray
            JSONArray array = new JSONArray(json);
            // Проход по всем объектам json
            for (int i = 0; i < array.length(); i++) {
                // Получение объекта
                JSONObject obj = array.getJSONObject(i);
                // Создание объекта монстра
                Bestiary monster = new Bestiary(
                        obj.getString("name"),
                        obj.getString("danger"),
                        obj.getString("type"),
                        obj.getString("ac"),
                        obj.getString("initiative"),
                        obj.getString("hp"),
                        obj.getString("move"),
                        obj.getString("abilities"),
                        obj.getString("skills"),
                        obj.getString("resists"),
                        obj.getString("immuns"),
                        obj.getString("feel"),
                        obj.getString("languages"),
                        obj.getString("area"),
                        obj.getString("exp"),
                        obj.getString("features"),
                        obj.getString("actions"),
                        obj.getString("legendaryActions"),
                        obj.getString("bonusActions"),
                        obj.getString("reactions")
                );
                // Добавление монстра в список
                list.add(monster);
            }

        } catch (Exception e) {
            // Вывод ошибки в лог
            e.printStackTrace();
        }
        return list;
    }

}