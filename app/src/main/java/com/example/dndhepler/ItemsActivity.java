package com.example.dndhepler;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.util.ArrayList;

public class ItemsActivity extends BaseActivity {
    private Toolbar toolbar;
    ArrayList<Items> items24 = new ArrayList<>();
    ArrayList<Items> items14 = new ArrayList<>();
    ArrayList<Items> filteredItems = new ArrayList<>();
    boolean useItems = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Магические предметы");
        RecyclerView recyclerView = findViewById(R.id.itemsView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        items24 = loadJson("Items5e24.json");
        items14 = loadJson("Items5e14.json");
        filteredItems.clear();
        if (!items14.isEmpty()) {
            filteredItems.addAll(items14);
        }
        Button filterButton = findViewById(R.id.filter);
        LinearLayout filterLayout = findViewById(R.id.filterLayout);
        filterButton.setOnClickListener(v -> {
            if (filterLayout.getVisibility() == View.GONE) {
                filterLayout.setVisibility(View.VISIBLE);
        } else {
                filterLayout.setVisibility(View.GONE);
            }
        });
        ItemsAdapter adapter = new ItemsAdapter(this, filteredItems);
        Switch itemsSwitch = findViewById(R.id.itemsSwitch);
        itemsSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            useItems = isChecked;
            filteredItems.clear();
            if (useItems) {
                filteredItems.addAll(items24);
            } else {
                filteredItems.addAll(items14);
            }
            adapter.notifyDataSetChanged();
        });
        recyclerView.setAdapter(adapter);
        SearchView searchView = findViewById(R.id.searchItemsView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filteredItems.clear();
                ArrayList<Items> currentList;
                if (useItems) {
                    currentList = items24;
                } else {
                    currentList = items14;
                }
                for (Items item : currentList) {
                    if (item.name.toLowerCase().contains(newText.toLowerCase())) {
                        filteredItems.add(item);
                    }
                }
                adapter.notifyDataSetChanged();
                return true;
            }
        });

    }

    private ArrayList<Items> loadJson(String fileName) {
        ArrayList<Items> list = new ArrayList<>();
        try {
            FileInputStream fis = openFileInput(fileName);
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            fis.close();
            String json = new String(bytes);
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                Items item = new Items(
                        obj.getString("name"),
                        obj.getString("aligment"),
                        obj.getString("price"),
                        obj.getString("description"),
                        obj.getString("tableDescription")
                );
                list.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}