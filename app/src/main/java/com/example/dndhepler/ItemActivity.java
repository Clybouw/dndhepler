package com.example.dndhepler;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

public class ItemActivity extends BaseActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_info);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView name = findViewById(R.id.item_name);
        TextView aligment = findViewById(R.id.item_aligment);
        TextView price = findViewById(R.id.item_price);
        TextView description = findViewById(R.id.item_description);
        TextView tableDescription = findViewById(R.id.item_tableDescription);
        String itemName = getIntent().getStringExtra("name");
        name.setText(itemName);
        String itemAligment = getIntent().getStringExtra("aligment");
        aligment.setText(itemAligment);
        String itemPrice = getIntent().getStringExtra("price");
        if (itemPrice != null) {
            price.setText(itemPrice);
        } else {
            price.setText("Стоимость не определена");
        }
        String itemDescription = getIntent().getStringExtra("description");
        description.setText(itemDescription);
        String itemTableDescription = getIntent().getStringExtra("tableDescription");
        tableDescription.setText(itemTableDescription);
    }
}
