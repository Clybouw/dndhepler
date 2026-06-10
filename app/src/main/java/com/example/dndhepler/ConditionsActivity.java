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

public class ConditionsActivity extends BaseActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conditions);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView conditionInfo = findViewById(R.id.conditionsInfo);

        try {
            //Путь к файлу
            FileInputStream fis = openFileInput("Conditions5e14.json");
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
            String unconscious = obj.getString("unconscious");
            String frightened = obj.getString("frightened");
            String exhaustion = obj.getString("exhaustion");
            String invisible = obj.getString("invisible");
            String incapacitated = obj.getString("incapacitated");
            String deafened = obj.getString("deafened");
            String petrified = obj.getString("petrified");
            String restrained = obj.getString("restrained");
            String blinded = obj.getString("blinded");
            String poisoned = obj.getString("poisoned");
            String charmed = obj.getString("charmed");
            String stunned = obj.getString("stunned");
            String paralyzed = obj.getString("paralyzed");
            String prone = obj.getString("prone");
            String grappled = obj.getString("grappled");
            // Вывод
            String result = name +
                    "\n\n" + description +
                    "\n\nСхождение с ума\n\n" + unconscious +
                    "\n\nИспуганный\n\n" + frightened +
                    "\n\nИстощенный\n\n" + exhaustion +
                    "\n\nНевидимый\n\n" + invisible +
                    "\n\nНедееспособный\n\n" + incapacitated +
                    "\n\nОглохший\n\n" + deafened +
                    "\n\nОкаменевший\n\n" + petrified +
                    "\n\nОпутанный\n\n" + restrained +
                    "\n\nОслеплённый\n\n" + blinded +
                    "\n\nОтравленный\n\n" + poisoned +
                    "\n\nОчарованный\n\n" + charmed +
                    "\n\nОшеломлённый\n\n" + stunned +
                    "\n\nПарализованный\n\n" + paralyzed +
                    "\n\nСбитый с ног / Лежащий ничком\n\n" + prone +
                    "\n\nСхваченный\n\n" + grappled;
            conditionInfo.setText(result);
        } catch (Exception e) {
            e.printStackTrace();
            conditionInfo.setText("Ошибка загрузки JSON");
        }

    }

}