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
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileInputStream;

public class MadnessActivity extends BaseActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_madness);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView madnessInfo = findViewById(R.id.madnessInfo);

        try {
            //Путь к файлу
            FileInputStream fis = openFileInput("Madness5e14.json");
            //Чтение файла
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            fis.close();
            // JSON строка
            String json = new String(bytes);
            //Парсим JSON
            JSONArray jsonArray = new JSONArray(json);
            JSONObject obj = jsonArray.getJSONObject(0);
            String name = obj.getString("name");
            String description = obj.getString("description");
            String status = obj.getString("status");
            String effects = obj.getString("effects");
            String shortMad = obj.getString("shortMad");
            String longMad = obj.getString("longMad");
            String indefiniteMad = obj.getString("indefiniteMad");
            String healingMad = obj.getString("healingMad");
            // Вывод
            String result = name +
                    "\n\n" + description +
                    "\n\nСхождение с ума\n\n" + status +
                    "\n\nЭффекты безумия\n\n" + effects +
                    "\n\nКратковременное безумие\n" + shortMad +
                    "\n\nДолговременное безумие\n" + longMad +
                    "\n\nБессрочное безумие\n" + indefiniteMad +
                    "\n\nЛечение безумия\n\n" + healingMad;

            madnessInfo.setText(result);

        } catch (Exception e) {
            e.printStackTrace();
            madnessInfo.setText("Ошибка загрузки JSON");
        }

    }

}