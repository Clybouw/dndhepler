package com.example.dndhepler;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button_bestiary = findViewById(R.id.button_bestiary);
        Button button_spells = findViewById(R.id.button_spells);
        Button button_mech = findViewById(R.id.button_mech);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        copyJsonFilesFirstLaunch();

        //Переход в бестиарий
        button_bestiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BestiaryActivity.class);
                startActivity(intent);
            }
        });

        button_spells.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SpellsActivity.class);
                startActivity(intent);
            }
        });

        button_mech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameMechanicActivity.class);
                startActivity(intent);
            }
        });
    }

    private void copyJsonFilesFirstLaunch() {
        SharedPreferences prefs = getSharedPreferences("app", MODE_PRIVATE);
        int currentDataVersion = 3; //Значение 3 для версии 0.4, обновить если нужно загрузить новые json
        int savedVersion = prefs.getInt("data_version", 0);
        if (savedVersion >= currentDataVersion) {
            return;}
        String[] files = {
                "Bestiary5e24.json",
                "Bestiary5e14.json",
                "Spells5e24.json",
                "Spells5e14.json",
                "Madness5e14.json",
                "Conditions5e14.json",
                "Armors5e14.json",
                "Weapons5e14.json"
        };
        try {
            for (String fileName : files) {
                InputStream is = getAssets().open(fileName);
                File outFile = new File(getFilesDir(), fileName);
                FileOutputStream fos = new FileOutputStream(outFile);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, length);
                }
                fos.flush();
                fos.close();
                is.close();
            }
            prefs.edit().putInt("data_version", currentDataVersion).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}