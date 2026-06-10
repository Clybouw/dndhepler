package com.example.dndhepler;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Switch;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileInputStream;
import java.util.ArrayList;
public class SpellsActivity extends AppCompatActivity {
    private Toolbar toolbar;
    ArrayList<Spells> spells24 = new ArrayList<>();
    ArrayList<Spells> spells14 = new ArrayList<>();
    ArrayList<Spells> filteredSpells = new ArrayList<>();

    boolean useSpells = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spells);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Заклинания");
        RecyclerView recyclerView = findViewById(R.id.spellsView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        spells24 = loadJson("Spells5e24.json");
        spells14 = loadJson("Spells5e14.json");
        filteredSpells.clear();
        if (!spells14.isEmpty()) {
            filteredSpells.addAll(spells14);
        }
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
        SpellsAdapter adapter = new SpellsAdapter(this, filteredSpells);
        Switch spellsSwitch = findViewById(R.id.spellsSwitch);
        spellsSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {{
            useSpells = isChecked;
            filteredSpells.clear();
            if (useSpells) {
                filteredSpells.addAll(spells24);
            } else {
                filteredSpells.addAll(spells14);
            }
            adapter.notifyDataSetChanged();
        }});
        recyclerView.setAdapter(adapter);
        SearchView searchView = findViewById(R.id.searchSpellsView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Очистка списка фильтрации
                filteredSpells.clear();
                // Проверка каждого монстра
                ArrayList<Spells> currentList;
                if (useSpells) {
                    currentList = spells24;
                } else {
                    currentList = spells14;
                }
                for (Spells spell : currentList) {
                    // Если имя содержит введенный текст
                    if (spell.name.toLowerCase().contains(newText.toLowerCase())) {
                        // Добавление монстра
                        filteredSpells.add(spell);
                    }
                }
                // Обновление RecyclerView
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    private ArrayList<Spells> loadJson(String fileName) {
        ArrayList<Spells> list = new ArrayList<>();
        try {
            FileInputStream fis = openFileInput(fileName);
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            fis.close();
            String json = new String(bytes);
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                Spells spell = new Spells(
                        obj.getString("name"),
                        obj.getString("level"),
                        obj.getString("casttime"),
                        obj.getString("range"),
                        obj.getString("components"),
                        obj.getString("duration"),
                        obj.getString("classes"),
                        obj.getString("subclasses"),
                        obj.getString("description"),
                        obj.getString("lvlup")
                );
                list.add(spell);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
