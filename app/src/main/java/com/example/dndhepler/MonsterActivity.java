package com.example.dndhepler;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MonsterActivity extends BaseActivity {

    private Toolbar toolbar;

    private void appendIfNotEmpty(StringBuilder sb, String value) {
        if (value == null) {
            return;
        }
        value = value.trim();
        if (value.isEmpty()) {
            return;
        }
        if (sb.length() > 0) {
            sb.append("\n\n");
        }
        sb.append(value);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monster);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView name = findViewById(R.id.unit_name);
        TextView info = findViewById(R.id.unit_info);
        String monsterName = getIntent().getStringExtra("name");
        name.setText(monsterName);
        StringBuilder result = new StringBuilder();
        appendIfNotEmpty(result, getIntent().getStringExtra("type"));
        appendIfNotEmpty(result, getIntent().getStringExtra("ac"));
        appendIfNotEmpty(result, getIntent().getStringExtra("initiative"));
        appendIfNotEmpty(result, getIntent().getStringExtra("hp"));
        appendIfNotEmpty(result, getIntent().getStringExtra("abilities"));
        appendIfNotEmpty(result, getIntent().getStringExtra("skills"));
        appendIfNotEmpty(result, getIntent().getStringExtra("resists"));
        appendIfNotEmpty(result, getIntent().getStringExtra("immuns"));
        appendIfNotEmpty(result, getIntent().getStringExtra("feel"));
        appendIfNotEmpty(result, getIntent().getStringExtra("languages"));
        appendIfNotEmpty(result, getIntent().getStringExtra("area"));
        appendIfNotEmpty(result, getIntent().getStringExtra("exp"));
        String features = getIntent().getStringExtra("features");
        if (features != null && !features.trim().isEmpty()) {
            appendIfNotEmpty(result, "Особенности:\n" + features);
        }
        String actions = getIntent().getStringExtra("actions");
        if (actions != null && !actions.trim().isEmpty()) {
            appendIfNotEmpty(result, "Действия:\n" + actions);
        }
        String legendaryActions = getIntent().getStringExtra("legendaryActions");
        if (legendaryActions != null && !legendaryActions.trim().isEmpty()) {
            appendIfNotEmpty(result, "Легендарные действия:\n" + legendaryActions);
        }
        String bonusActions = getIntent().getStringExtra("bonusActions");
        if (bonusActions != null && !bonusActions.trim().isEmpty()) {
            appendIfNotEmpty(result, "Бонусные действия:\n" + bonusActions);
        }
        String reactions = getIntent().getStringExtra("reactions");
        if (reactions != null && !reactions.trim().isEmpty()) {
            appendIfNotEmpty(result, "Реакции:\n" + reactions);
        }
        info.setText(result.toString());

    }

}