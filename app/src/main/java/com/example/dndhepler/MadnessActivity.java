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
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileInputStream;

public class MadnessActivity extends AppCompatActivity{

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

    // Создание меню toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Подключение toolbar_menu.xml
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    // Нажатие на элемент меню
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Проверка кнопки обновления
        if (item.getItemId() == R.id.update) {
            //Загрузка окна
            View dialogView = getLayoutInflater().inflate(R.layout.load_data, null);
            //Получаем чекбоксы
            CheckBox bestiary24Load = dialogView.findViewById(R.id.bestiary24Load);
            CheckBox bestiary14Load = dialogView.findViewById(R.id.bestiary14Load);
            CheckBox spells24Load = dialogView.findViewById(R.id.spells24Load);
            CheckBox spells14Load = dialogView.findViewById(R.id.spells14Load);
            //Получаем кнопки
            Button cancel = dialogView.findViewById(R.id.cancel);
            Button start = dialogView.findViewById(R.id.start);
            //Создаем окно
            AlertDialog dialog = new AlertDialog.Builder(this).setView(dialogView).create();
            dialog.show();
            //Кнопка отмены
            cancel.setOnClickListener(v -> {
                dialog.dismiss();
            });
            //Кнопка старта выгрузки
            start.setOnClickListener(v -> {
                LoadingDialog.show(this,
                        bestiary24Load.isChecked(),
                        bestiary14Load.isChecked(),
                        spells24Load.isChecked(),
                        spells14Load.isChecked());
            });
            return true;
        }
        if (item.getItemId() == R.id.bugreport) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/club238923264"));
            startActivity(intent);
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }
}