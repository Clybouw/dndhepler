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

public class SorceryActivity extends AppCompatActivity{

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
