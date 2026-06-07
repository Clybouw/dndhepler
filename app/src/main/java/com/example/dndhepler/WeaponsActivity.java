package com.example.dndhepler;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class WeaponsActivity extends BaseActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapons);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView weaponsDescription = findViewById(R.id.weaponsDescription);
        TextView possessionWeaponsName = findViewById(R.id.possessionWeaponsName);
        TextView possessionWeaponsDescription = findViewById(R.id.possessionWeaponsDescription);
        TextView propertyWeaponsName = findViewById(R.id.propertyWeaponsName);
        RecyclerView propertyWeaponsView = findViewById(R.id.propertyWeaponsView);
        TextView weapons = findViewById(R.id.weapons);
        RecyclerView weaponsView = findViewById(R.id.weaponsView);
        TextView improvisedWeaponsName = findViewById(R.id.improvisedWeaponsName);
        TextView improvisedWeaponsDescription = findViewById(R.id.improvisedWeaponsDescription);
        TextView silverWeaponsName = findViewById(R.id.silverWeaponsName);
        TextView silverWeaponsDescription = findViewById(R.id.silverWeaponsDescription);
        TextView adamantiteWeaponsName = findViewById(R.id.adamantiteWeaponsName);
        TextView adamantiteWeaponsDescription = findViewById(R.id.adamantiteWeaponsDescription);

        try {
            FileInputStream fis = openFileInput("Weapons5e14.json");
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            fis.close();
            String json = new String(bytes);
            JSONArray jsonArray = new JSONArray(json);
            JSONObject obj = jsonArray.getJSONObject(0);
            String weaponsName = obj.getString("name");//Поиск названия в json
            getSupportActionBar().setTitle(weaponsName);//Вывод в бар названия
            String weaponsListDescription = obj.getString("description");//Поиск описания страницы в json
            weaponsDescription.setText(weaponsListDescription);//Вывод описания страницы
            //Поиск владений оружием в json
            JSONObject possessionWeaponsList = obj.getJSONObject("possessionWeapons");
            String possessionWeaponsListName = possessionWeaponsList.getString("name");
            String possessionWeaponsListDescription = possessionWeaponsList.getString("description");
            //Вывод владений оружием
            possessionWeaponsName.setText(possessionWeaponsListName);
            possessionWeaponsDescription.setText(possessionWeaponsListDescription);
            //Поиск свойств оружия в json
            JSONObject propertyWeaponsList = obj.getJSONObject("propertyWeapons");
            String propertyWeaponsListName = propertyWeaponsList.getString("name");
            Iterator<String> propertyKeys = propertyWeaponsList.keys();
            ArrayList<PropertyWeapons> propertyWeaponsListArray = new ArrayList<>();
            while (propertyKeys.hasNext()) {
                String propertyKey = propertyKeys.next();
                if (propertyKey.equals("name") || propertyKey.equals("description")) {
                    continue;
                }
                JSONObject propertyObject = propertyWeaponsList.getJSONObject(propertyKey);
                String name = propertyObject.getString("name");
                String description = propertyObject.getString("description");
                propertyWeaponsListArray.add(new PropertyWeapons(name, description));
            }
            //Вывод свойств оружия
            propertyWeaponsName.setText(propertyWeaponsListName);
            propertyWeaponsView.setLayoutManager(new LinearLayoutManager(this));
            PropertyWeaponsAdapter adapter = new PropertyWeaponsAdapter(propertyWeaponsListArray);
            propertyWeaponsView.setAdapter(adapter);
            //Поиск списка оружий в json
            JSONObject weaponsList = obj.getJSONObject("weapons");
            String weaponsListName = weaponsList.getString("name");
            ArrayList<CategoryWeapons> weaponsListArray = new ArrayList<>();
            Iterator<String> weaponsKeys = weaponsList.keys();
            while (weaponsKeys.hasNext()) {
                String categoryKey = weaponsKeys.next();
                if (categoryKey.equals("name")) {
                    continue;
                }
                JSONObject categoryObject = weaponsList.getJSONObject(categoryKey);
                String categoryName = categoryObject.getString("name");
                weaponsListArray.add(new CategoryWeapons(CategoryWeapons.TYPE_CATEGORY, categoryName, null));
                Iterator<String> categoryKeys = categoryObject.keys();
                while (categoryKeys.hasNext()) {
                    String weaponsKey = categoryKeys.next();
                    if (weaponsKey.equals("name")) {
                        continue;
                    }
                    JSONObject weaponsObject = categoryObject.getJSONObject(weaponsKey);
                    String name = weaponsObject.getString("name");
                    String description = weaponsObject.optString("description", "");
                    String price = weaponsObject.getString("price");
                    String damage = weaponsObject.getString("damage");
                    String weight = weaponsObject.getString("weight");
                    String property = weaponsObject.getString("property");
                    Weapons weapon = new Weapons(name,
                            description,
                            price,
                            damage,
                            weight,
                            property);
                    weaponsListArray.add(new CategoryWeapons(CategoryWeapons.TYPE_WEAPONS, null, weapon));
                }
            }
            //Вывод списка оружий
            weapons.setText(weaponsListName);
            weaponsView.setLayoutManager(new LinearLayoutManager(this));
            weaponsView.setNestedScrollingEnabled(false);
            WeaponsAdapter weaponsAdapter = new WeaponsAdapter(weaponsListArray);
            weaponsView.setAdapter(weaponsAdapter);
            //Импровизированное оружие
            JSONObject improvisedWeaponsList = obj.getJSONObject("improvisedWeapons");
            String improvisedWeaponsListName = improvisedWeaponsList.getString("name");
            String improvisedWeaponsListDescription = improvisedWeaponsList.getString("description");
            //Вывод импровизированного оружия
            improvisedWeaponsName.setText(improvisedWeaponsListName);
            improvisedWeaponsDescription.setText(improvisedWeaponsListDescription);
            //Посеребренное оружие
            JSONObject silverWeaponsList = obj.getJSONObject("silverWeapons");
            String silverWeaponsListName = silverWeaponsList.getString("name");
            String silverWeaponsListDescription = silverWeaponsList.getString("description");
            //Вывод посеребренного оружия
            silverWeaponsName.setText(silverWeaponsListName);
            silverWeaponsDescription.setText(silverWeaponsListDescription);
            //Адамантитовое оружие
            JSONObject adamantiteWeaponsList = obj.getJSONObject("adamantiteWeapons");
            String adamantiteWeaponsListName = adamantiteWeaponsList.getString("name");
            String adamantiteWeaponsListDescription = adamantiteWeaponsList.getString("description");
            //Вывод адамантитового оружия
            adamantiteWeaponsName.setText(adamantiteWeaponsListName);
            adamantiteWeaponsDescription.setText(adamantiteWeaponsListDescription);
        } catch (Exception e) {
            e.printStackTrace();
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
