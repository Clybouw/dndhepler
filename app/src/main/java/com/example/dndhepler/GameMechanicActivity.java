package com.example.dndhepler;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

public class GameMechanicActivity extends BaseActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamemechanic);
        // Получение Toolbar из layout
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView madnessView = findViewById(R.id.madness_view);
        TextView conditionsView = findViewById(R.id.condition_view);
        TextView armorsView = findViewById(R.id.armor_view);
        TextView weaponsView = findViewById(R.id.weapon_view);
        TextView itemsView = findViewById(R.id.items_view);
        TextView actionsView = findViewById(R.id.actions_view);

        madnessView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameMechanicActivity.this, MadnessActivity.class);
                startActivity(intent);
            }
        });

        conditionsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameMechanicActivity.this, ConditionsActivity.class);
                startActivity(intent);
            }
        });

        armorsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameMechanicActivity.this, ArmorsActivity.class);
                startActivity(intent);
            }
        });

        weaponsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameMechanicActivity.this, WeaponsActivity.class);
                startActivity(intent);
            }
        });

        itemsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameMechanicActivity.this, ItemsActivity.class);
                startActivity(intent);
            }
        });

        actionsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameMechanicActivity.this, ActionsActivity.class);
                startActivity(intent);
            }
        });

    }


}
