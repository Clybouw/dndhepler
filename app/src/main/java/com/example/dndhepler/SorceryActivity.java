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
import androidx.appcompat.widget.Toolbar;

public class SorceryActivity extends BaseActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorcery);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView name = findViewById(R.id.sorcery_name);
        TextView info = findViewById(R.id.sorcery_info);
        String spellName = getIntent().getStringExtra("name");
        name.setText(spellName);
        String spellInfo =
                getIntent().getStringExtra("casttime") +
                        "\n" + getIntent().getStringExtra("range") +
                        "\n" + getIntent().getStringExtra("components") +
                        "\n" + getIntent().getStringExtra("duraction") +
                        "\n" + getIntent().getStringExtra("classes") +
                        "\n" + getIntent().getStringExtra("subclasses") +
                        "\n" + getIntent().getStringExtra("description") +
                        "\n" + getIntent().getStringExtra("lvlup");
        info.setText(spellInfo);
    }

}
