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
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;

public class ArmorsActivity extends BaseActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armors);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView armorsListName = findViewById(R.id.armorName);
        TextView useArmors = findViewById(R.id.useArmors);
        TextView ACArmors = findViewById(R.id.ACArmors);
        TextView heavyArmorsInfo = findViewById(R.id.heavyArmorsInfo);
        TextView stealth = findViewById(R.id.stealth);
        TextView shieldsInfo = findViewById(R.id.shieldsInfo);
        TextView armors = findViewById(R.id.armors);
        //Легкие доспехи
        TextView lightArmorsList = findViewById(R.id.lightArmors);
        TextView quilted = findViewById(R.id.quilted);
        LinearLayout quiltedLayout = findViewById(R.id.quiltedLayout);
        TextView quiltedInfo = findViewById(R.id.quiltedInfo);
        quilted.setOnClickListener(v -> {
            if (quiltedLayout.getVisibility() == View.GONE) {
                quiltedLayout.setVisibility(View.VISIBLE);
            } else {
                quiltedLayout.setVisibility(View.GONE);
            }
        });
        TextView leather = findViewById(R.id.leather);
        LinearLayout leatherLayout = findViewById(R.id.leatherLayout);
        TextView leatherInfo = findViewById(R.id.leatherInfo);
        leather.setOnClickListener(v -> {
            if (leatherLayout.getVisibility() == View.GONE) {
                leatherLayout.setVisibility(View.VISIBLE);
            } else {
                leatherLayout.setVisibility(View.GONE);
            }
        });
        TextView rivetedLeather = findViewById(R.id.rivetedLeather);
        LinearLayout rivetedLeatherLayout = findViewById(R.id.rivetedLeatherLayout);
        TextView rivetedLeatherInfo = findViewById(R.id.rivetedLeatherInfo);
        rivetedLeather.setOnClickListener(v -> {
            if (rivetedLeatherLayout.getVisibility() == View.GONE) {
                rivetedLeatherLayout.setVisibility(View.VISIBLE);
            } else {
                rivetedLeatherLayout.setVisibility(View.GONE);
            }
        });
        //Средние доспехи
        TextView mediumArmorsList = findViewById(R.id.mediumArmors);
        TextView selfish = findViewById(R.id.selfish);
        LinearLayout selfishLayout = findViewById(R.id.selfishLayout);
        TextView selfishInfo = findViewById(R.id.selfishInfo);
        selfish.setOnClickListener(v -> {
            if (selfishLayout.getVisibility() == View.GONE) {
                selfishLayout.setVisibility(View.VISIBLE);
            } else {
                selfishLayout.setVisibility(View.GONE);
            }
        });
        TextView chainmailShirt = findViewById(R.id.chainmailShirt);
        LinearLayout chainmailShirtLayout = findViewById(R.id.chainmailShirtLayout);
        TextView chainmailShirtInfo = findViewById(R.id.chainmailShirtInfo);
        chainmailShirt.setOnClickListener(v -> {
            if (chainmailShirtLayout.getVisibility() == View.GONE) {
                chainmailShirtLayout.setVisibility(View.VISIBLE);
            } else {
                chainmailShirtLayout.setVisibility(View.GONE);
            }
        });
        TextView scaly = findViewById(R.id.scaly);
        LinearLayout scalyLayout = findViewById(R.id.scalyLayout);
        TextView scalyInfo = findViewById(R.id.scalyInfo);
        scaly.setOnClickListener(v -> {
            if (scalyLayout.getVisibility() == View.GONE) {
                scalyLayout.setVisibility(View.VISIBLE);
            } else {
                scalyLayout.setVisibility(View.GONE);
            }
        });
        TextView cuirass = findViewById(R.id.cuirass);
        LinearLayout cuirassLayout = findViewById(R.id.cuirassLayout);
        TextView cuirassInfo = findViewById(R.id.cuirassInfo);
        cuirass.setOnClickListener(v -> {
            if (cuirassLayout.getVisibility() == View.GONE) {
                cuirassLayout.setVisibility(View.VISIBLE);
            } else {
                cuirassLayout.setVisibility(View.GONE);
            }
        });
        //Тяжелые доспехи
        TextView heavyArmorsList = findViewById(R.id.heavyArmors);
        TextView ringed = findViewById(R.id.ringed);
        LinearLayout ringedLayout = findViewById(R.id.ringedLayout);
        TextView ringedInfo = findViewById(R.id.ringedInfo);
        ringed.setOnClickListener(v -> {
            if (ringedLayout.getVisibility() == View.GONE) {
                ringedLayout.setVisibility(View.VISIBLE);
            } else {
                ringedLayout.setVisibility(View.GONE);
            }
        });
        TextView chainMail = findViewById(R.id.chainMail);
        LinearLayout chainMailLayout = findViewById(R.id.chainMailLayout);
        TextView chainMailInfo = findViewById(R.id.chainMailInfo);
        chainMail.setOnClickListener(v -> {
            if (chainMailLayout.getVisibility() == View.GONE) {
                chainMailLayout.setVisibility(View.VISIBLE);
            } else {
                chainMailLayout.setVisibility(View.GONE);
            }
        });
        TextView composite = findViewById(R.id.composite);
        LinearLayout compositeLayout = findViewById(R.id.compositeLayout);
        TextView compositeInfo = findViewById(R.id.compositeInfo);
        composite.setOnClickListener(v -> {
            if (compositeLayout.getVisibility() == View.GONE) {
                compositeLayout.setVisibility(View.VISIBLE);
            } else {
                compositeLayout.setVisibility(View.GONE);
            }
        });
        TextView lats = findViewById(R.id.lats);
        LinearLayout latsLayout = findViewById(R.id.latsLayout);
        TextView latsInfo = findViewById(R.id.latsInfo);
        lats.setOnClickListener(v -> {
            if (latsLayout.getVisibility() == View.GONE) {
                latsLayout.setVisibility(View.VISIBLE);
            } else {
                latsLayout.setVisibility(View.GONE);
            }
        });
        //Щиты
        TextView shieldsList = findViewById(R.id.shields);
        TextView shield = findViewById(R.id.shield);
        LinearLayout shieldsLayout = findViewById(R.id.shieldLayout);
        TextView shieldInfo = findViewById(R.id.shieldInfo);
        shield.setOnClickListener(v -> {
            if (shieldsLayout.getVisibility() == View.GONE) {
                shieldsLayout.setVisibility(View.VISIBLE);
            } else {
                shieldsLayout.setVisibility(View.GONE);
            }
        });
        //Надевание и снятие доспехов
        TextView wearRemoveArmorsList = findViewById(R.id.wearRemoveArmors);
        TextView wearRemoveLightArmor = findViewById(R.id.wearRemovelightArmor);
        TextView wearRemoveMediumArmor = findViewById(R.id.wearRemoveMediumArmor);
        TextView wearRemoveHeavyArmor = findViewById(R.id.wearRemoveHeavyArmor);
        TextView wearRemoveShield = findViewById(R.id.wearRemoveShield);
        //Иные доспехи
        TextView anotherArmorsList = findViewById(R.id.anotherArmors);
        TextView coverSurvival = findViewById(R.id.coverSurvival);
        LinearLayout coverSurvivalLayout = findViewById(R.id.coverSurvivalLayout);
        TextView coverSurvivalInfo = findViewById(R.id.coverSurvivalInfo);
        coverSurvival.setOnClickListener(v -> {
            if (coverSurvivalLayout.getVisibility() == View.GONE) {
                coverSurvivalLayout.setVisibility(View.VISIBLE);
            } else {
                coverSurvivalLayout.setVisibility(View.GONE);
            }
        });

        try {
            FileInputStream fis = openFileInput("Armors5e14.json");
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            fis.close();
            String json = new String(bytes);
            JSONArray jsonArray = new JSONArray(json);
            JSONObject obj = jsonArray.getJSONObject(0);
            //Название страницы
            String ArmorListName = obj.getString("name");
            //Владение доспехами
            JSONObject useArmor = obj.getJSONObject("useArmors");
            String useArmorName = useArmor.getString("name");
            String useArmorDescription = useArmor.getString("description");
            //Класс доспеха
            JSONObject ACArmor = obj.getJSONObject("ACArmors");
            String ACArmorName = ACArmor.getString("name");
            String ACArmorDescription = ACArmor.getString("description");
            //Тяжелые доспехи
            JSONObject heavyArmorInfoList = obj.getJSONObject("heavyArmors");
            String heavyArmorInfoName = heavyArmorInfoList.getString("name");
            String heavyArmorInfoDescription = heavyArmorInfoList.getString("description");
            //Скрытность
            JSONObject stealthList = obj.getJSONObject("stealth");
            String stealthName = stealthList.getString("name");
            String stealthDescription = stealthList.getString("description");
            //Щиты
            JSONObject shieldInfoList = obj.getJSONObject("shields");
            String shieldInfoName = shieldInfoList.getString("name");
            String shieldInfoDescription = shieldInfoList.getString("description");
            //Доспехи
            JSONObject armor = obj.getJSONObject("armors");
            String armorName = armor.getString("name");
            //Класс доспехов
            JSONObject allArmors = obj.getJSONObject("armors");
            JSONObject lightArmorList = allArmors.getJSONObject("lightArmors");
            String lightArmorName = lightArmorList.getString("name");
            JSONObject mediumArmorList = allArmors.getJSONObject("mediumArmors");
            String mediumArmorName = mediumArmorList.getString("name");
            JSONObject heavyArmorList = allArmors.getJSONObject("heavyArmors");
            String heavyArmorName = heavyArmorList.getString("name");
            JSONObject shieldList = allArmors.getJSONObject("shields");
            String shieldName = shieldList.getString("name");
            JSONObject anotherArmorList = allArmors.getJSONObject("anotherArmors");
            String anotherArmorName = anotherArmorList.getString("name");
            lightArmorsList.setText(lightArmorName);
            mediumArmorsList.setText(mediumArmorName);
            heavyArmorsList.setText(heavyArmorName);
            shieldsList.setText(shieldName);
            anotherArmorsList.setText(anotherArmorName);
            //Стеганый
            JSONObject quiltedList = lightArmorList.getJSONObject("quilted");
            String quiltedListName = quiltedList.getString("name");
            String quiltedListDescription = quiltedList.getString("description");
            String quiltedListPrice = quiltedList.getString("price");
            String quiltedListAC = quiltedList.getString("AC");
            String quiltedListStrength = quiltedList.getString("strength");
            String quiltedListStealth = quiltedList.getString("stealth");
            String quiltedListWeight = quiltedList.getString("weight");
            //Кожаный
            JSONObject leatherList = lightArmorList.getJSONObject("leather");
            String leatherListName = leatherList.getString("name");
            String leatherListDescription = leatherList.getString("description");
            String leatherListPrice = leatherList.getString("price");
            String leatherListAC = leatherList.getString("AC");
            String leatherListStrength = leatherList.getString("strength");
            String leatherListStealth = leatherList.getString("stealth");
            String leatherListWeight = leatherList.getString("weight");
            //Проклепанный кожаный
            JSONObject rivetedLeatherList = lightArmorList.getJSONObject("rivetedLeather");
            String rivetedLeatherListName = rivetedLeatherList.getString("name");
            String rivetedLeatherListDescription = rivetedLeatherList.getString("description");
            String rivetedLeatherListPrice = rivetedLeatherList.getString("price");
            String rivetedLeatherListAC = rivetedLeatherList.getString("AC");
            String rivetedLeatherListStrength = rivetedLeatherList.getString("strength");
            String rivetedLeatherListStealth = rivetedLeatherList.getString("stealth");
            String rivetedLeatherListWeight = rivetedLeatherList.getString("weight");
            //Шкурный
            JSONObject selfishList = mediumArmorList.getJSONObject("selfish");
            String selfishListName = selfishList.getString("name");
            String selfishListDescription = selfishList.getString("description");
            String selfishListPrice = selfishList.getString("price");
            String selfishListAC = selfishList.getString("AC");
            String selfishListStrength = selfishList.getString("strength");
            String selfishListStealth = selfishList.getString("stealth");
            String selfishListWeight = selfishList.getString("weight");
            //Кольчужная рубаха
            JSONObject chainmailShirtList = mediumArmorList.getJSONObject("chainmailShirt");
            String chainmailShirtListName = chainmailShirtList.getString("name");
            String chainmailShirtListDescription = chainmailShirtList.getString("description");
            String chainmailShirtListPrice = chainmailShirtList.getString("price");
            String chainmailShirtListAC = chainmailShirtList.getString("AC");
            String chainmailShirtListStrength = chainmailShirtList.getString("strength");
            String chainmailShirtListStealth = chainmailShirtList.getString("stealth");
            String chainmailShirtListWeight = chainmailShirtList.getString("weight");
            //Чешуйчатый
            JSONObject scalyList = mediumArmorList.getJSONObject("scaly");
            String scalyListName = scalyList.getString("name");
            String scalyListDescription = scalyList.getString("description");
            String scalyListPrice = scalyList.getString("price");
            String scalyListAC = scalyList.getString("AC");
            String scalyListStrength = scalyList.getString("strength");
            String scalyListStealth = scalyList.getString("stealth");
            String scalyListWeight = scalyList.getString("weight");
            //Кираса
            JSONObject cuirassList = mediumArmorList.getJSONObject("cuirass");
            String cuirassListName = cuirassList.getString("name");
            String cuirassListDescription = cuirassList.getString("description");
            String cuirassListPrice = cuirassList.getString("price");
            String cuirassListAC = cuirassList.getString("AC");
            String cuirassListStrength = cuirassList.getString("strength");
            String cuirassListStealth = cuirassList.getString("stealth");
            String cuirassListWeight = cuirassList.getString("weight");
            //Колечный
            JSONObject ringedList = heavyArmorList.getJSONObject("ringed");
            String ringedListName = ringedList.getString("name");
            String ringedListDescription = ringedList.getString("description");
            String ringedListPrice = ringedList.getString("price");
            String ringedListAC = ringedList.getString("AC");
            String ringedListStrength = ringedList.getString("strength");
            String ringedListStealth = ringedList.getString("stealth");
            String ringedListWeight = ringedList.getString("weight");
            //Кольчуга
            JSONObject chainMailList = heavyArmorList.getJSONObject("chainMail");
            String chainMailListName = chainMailList.getString("name");
            String chainMailListDescription = chainMailList.getString("description");
            String chainMailListPrice = chainMailList.getString("price");
            String chainMailListAC = chainMailList.getString("AC");
            String chainMailListStrength = chainMailList.getString("strength");
            String chainMailListStealth = chainMailList.getString("stealth");
            String chainMailListWeight = chainMailList.getString("weight");
            //Наборный
            JSONObject compositeList = heavyArmorList.getJSONObject("composite");
            String compositeListName = compositeList.getString("name");
            String compositeListDescription = compositeList.getString("description");
            String compositeListPrice = compositeList.getString("price");
            String compositeListAC = compositeList.getString("AC");
            String compositeListStrength = compositeList.getString("strength");
            String compositeListStealth = compositeList.getString("stealth");
            String compositeListWeight = compositeList.getString("weight");
            //Латы
            JSONObject latsList = heavyArmorList.getJSONObject("lats");
            String latsListName = latsList.getString("name");
            String latsListDescription = latsList.getString("description");
            String latsListPrice = latsList.getString("price");
            String latsListAC = latsList.getString("AC");
            String latsListStrength = latsList.getString("strength");
            String latsListStealth = latsList.getString("stealth");
            String latsListWeight = latsList.getString("weight");
            //Щит
            JSONObject shield1List = shieldList.getJSONObject("shield");
            String shield1ListName = shield1List.getString("name");
            String shield1ListDescription = shield1List.getString("description");
            String shield1ListPrice = shield1List.getString("price");
            String shield1ListAC = shield1List.getString("AC");
            String shield1ListStrength = shield1List.getString("strength");
            String shield1ListStealth = shield1List.getString("stealth");
            String shield1ListWeight = shield1List.getString("weight");
            //Покров выживания
            JSONObject coverSurvivalList = anotherArmorList.getJSONObject("coverSurvival");
            String coverSurvivalListName = coverSurvivalList.getString("name");
            String coverSurvivalListDescription = coverSurvivalList.getString("description");
            String coverSurvivalListPrice = coverSurvivalList.getString("price");
            String coverSurvivalListAC = coverSurvivalList.getString("AC");
            String coverSurvivalListStrength = coverSurvivalList.getString("strength");
            String coverSurvivalListStealth = coverSurvivalList.getString("stealth");
            String coverSurvivalListWeight = coverSurvivalList.getString("weight");
            //Надевание и снятие доспехов
            JSONObject wearRemoveArmors = obj.getJSONObject("wearRemoveArmors");
            String wearRemoveArmorsName = wearRemoveArmors.getString("name");
            JSONObject wearRemoveLightArmors = wearRemoveArmors.getJSONObject("lightArmor");
            String wearLightArmors = wearRemoveLightArmors.getString("wear");
            String removeLightArmors = wearRemoveLightArmors.getString("remove");
            JSONObject wearRemoveMediumArmors = wearRemoveArmors.getJSONObject("mediumArmor");
            String wearMediumArmors = wearRemoveMediumArmors.getString("wear");
            String removeMediumArmors = wearRemoveMediumArmors.getString("remove");
            JSONObject wearRemoveHeavyArmors = wearRemoveArmors.getJSONObject("heavyArmor");
            String wearHeavyArmors = wearRemoveHeavyArmors.getString("wear");
            String removeHeavyArmors = wearRemoveHeavyArmors.getString("remove");
            JSONObject wearRemoveShields = wearRemoveArmors.getJSONObject("shield");
            String wearShields = wearRemoveShields.getString("wear");
            String removeShields = wearRemoveShields.getString("remove");
            //Вывод
            //Название
            String Armors1ListName = ArmorListName;
            armorsListName.setText(Armors1ListName);
            //Владение доспехами
            String useArmorsListName = useArmorName;
            String useArmorsListDescription = useArmorDescription;
            setBoldText(useArmors, useArmorsListName, useArmorsListDescription);
            //Класс доспеха
            String ACArmorsListName = ACArmorName;
            String ACArmorsListDescription = ACArmorDescription;
            setBoldText(ACArmors, ACArmorsListName, ACArmorsListDescription);
            //Тяжелые доспехи
            String heavyArmorsListName = heavyArmorInfoName;
            String heavyArmorsListDescription = heavyArmorInfoDescription;
            setBoldText(heavyArmorsInfo, heavyArmorsListName, heavyArmorsListDescription);
            //Скрытность
            String stealthListName = stealthName;
            String stealthListDescription = stealthDescription;
            setBoldText(stealth, stealthListName, stealthListDescription);
            //Щиты
            String shieldsListName = shieldInfoName;
            String shieldsListDescription = shieldInfoDescription;
            setBoldText(shieldsInfo, shieldsListName, shieldsListDescription);
            //Доспехи
            String armorsName = armorName;
            armors.setText(armorsName);
            //Стеганый
            quilted.setText(quiltedListName);
            quiltedInfo.setText(quiltedListDescription +
                    "\n\nЦена: " + quiltedListPrice +
                    "\nКласс доспеха (КД): " + quiltedListAC +
                    "\nСила: " + quiltedListStrength +
                    "\nСкрытность: " + quiltedListStealth +
                    "\nВес: " + quiltedListWeight);
            //Кожаный
            leather.setText(leatherListName);
            leatherInfo.setText(leatherListDescription +
                    "\n\nЦена: " + leatherListPrice +
                    "\nКласс доспеха (КД): " + leatherListAC +
                    "\nСила: " + leatherListStrength +
                    "\nСкрытность: " + leatherListStealth +
                    "\nВес: " + leatherListWeight);
            //Проклепанный кожаный
            rivetedLeather.setText(rivetedLeatherListName);
            rivetedLeatherInfo.setText(rivetedLeatherListDescription +
                    "\n\nЦена: " + rivetedLeatherListPrice +
                    "\nКласс доспеха (КД): " + rivetedLeatherListAC +
                    "\nСила: " + rivetedLeatherListStrength +
                    "\nСкрытность: " + rivetedLeatherListStealth +
                    "\nВес: " + rivetedLeatherListWeight);
            //Шкурный
            selfish.setText(selfishListName);
            selfishInfo.setText(selfishListDescription +
                    "\n\nЦена: " + selfishListPrice +
                    "\nКласс доспеха (КД): " + selfishListAC +
                    "\nСила: " + selfishListStrength +
                    "\nСкрытность: " + selfishListStealth +
                    "\nВес: " + selfishListWeight);
            //Кольчужная рубаха
            chainmailShirt.setText(chainmailShirtListName);
            chainmailShirtInfo.setText(chainmailShirtListDescription +
                    "\n\nЦена: " + chainmailShirtListPrice +
                    "\nКласс доспеха (КД): " + chainmailShirtListAC +
                    "\nСила: " + chainmailShirtListStrength +
                    "\nСкрытность: " + chainmailShirtListStealth +
                    "\nВес: " + chainmailShirtListWeight);
            //Чешуйчатый
            scaly.setText(scalyListName);
            scalyInfo.setText(scalyListDescription +
                    "\n\nЦена: " + scalyListPrice +
                    "\nКласс доспеха (КД): " + scalyListAC +
                    "\nСила: " + scalyListStrength +
                    "\nСкрытность: " + scalyListStealth +
                    "\nВес: " + scalyListWeight);
            //Кираса
            cuirass.setText(cuirassListName);
            cuirassInfo.setText(cuirassListDescription +
                    "\n\nЦена: " + cuirassListPrice +
                    "\nКласс доспеха (КД): " + cuirassListAC +
                    "\nСила: " + cuirassListStrength +
                    "\nСкрытность: " + cuirassListStealth +
                    "\nВес: " + cuirassListWeight);
            //Колечный
            ringed.setText(ringedListName);
            ringedInfo.setText(ringedListDescription +
                    "\n\nЦена: " + ringedListPrice +
                    "\nКласс доспеха (КД): " + ringedListAC +
                    "\nСила: " + ringedListStrength +
                    "\nСкрытность: " + ringedListStealth +
                    "\nВес: " + ringedListWeight);
            //Кольчуга
            chainMail.setText(chainMailListName);
            chainMailInfo.setText(chainMailListDescription +
                    "\n\nЦена: " + chainMailListPrice +
                    "\nКласс доспеха (КД): " + chainMailListAC +
                    "\nСила: " + chainMailListStrength +
                    "\nСкрытность: " + chainMailListStealth +
                    "\nВес: " + chainMailListWeight);
            //Наборный
            composite.setText(compositeListName);
            compositeInfo.setText(compositeListDescription +
                    "\n\nЦена: " + compositeListPrice +
                    "\nКласс доспеха (КД): " + compositeListAC +
                    "\nСила: " + compositeListStrength +
                    "\nСкрытность: " + compositeListStealth +
                    "\nВес: " + compositeListWeight);
            //Латы
            lats.setText(latsListName);
            latsInfo.setText(latsListDescription +
                    "\n\nЦена: " + latsListPrice +
                    "\nКласс доспеха (КД): " + latsListAC +
                    "\nСила: " + latsListStrength +
                    "\nСкрытность: " + latsListStealth +
                    "\nВес: " + latsListWeight);
            //Щит
            shield.setText(shield1ListName);
            shieldInfo.setText(shield1ListDescription +
                    "\n\nЦена: " + shield1ListPrice +
                    "\nКласс доспеха (КД): " + shield1ListAC +
                    "\nСила: " + shield1ListStrength +
                    "\nСкрытность: " + shield1ListStealth +
                    "\nВес: " + shield1ListWeight);
            //Покров выживания
            coverSurvival.setText(coverSurvivalListName);
            coverSurvivalInfo.setText(coverSurvivalListDescription +
                    "\n\nЦена: " + coverSurvivalListPrice +
                    "\nКласс доспеха (КД): " + coverSurvivalListAC +
                    "\nСила: " + coverSurvivalListStrength +
                    "\nСкрытность: " + coverSurvivalListStealth +
                    "\nВес: " + coverSurvivalListWeight);
            //Надевание и снятие доспехов
            wearRemoveArmorsList.setText(wearRemoveArmorsName);
            String wearRemoveLightArmorName = "Легкий доспех";
            String wearLightArmor = wearLightArmors;
            String removeLightArmor = removeLightArmors;
            setBoldWRText(wearRemoveLightArmor, wearRemoveLightArmorName, wearLightArmor, removeLightArmor);
            String wearRemoveMediumArmorName = "Средний доспех";
            String wearMediumArmor = wearMediumArmors;
            String removeMediumArmor = removeMediumArmors;
            setBoldWRText(wearRemoveMediumArmor, wearRemoveMediumArmorName, wearMediumArmor, removeMediumArmor);
            String wearRemoveHeavyArmorName = "Тяжелый доспех";
            String wearHeavyArmor = wearHeavyArmors;
            String removeHeavyArmor = removeHeavyArmors;
            setBoldWRText(wearRemoveHeavyArmor, wearRemoveHeavyArmorName, wearHeavyArmor, removeHeavyArmor);
            String wearRemoveShieldName = "Щит";
            String wearShield = wearShields;
            String removeShield = removeShields;
            setBoldWRText(wearRemoveShield, wearRemoveShieldName, wearShield, removeShield);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void setBoldText(TextView textView, String name, String description) {
        String nameList = name + ". ";
        String fullText = nameList + description;
        SpannableString spannable = new SpannableString(fullText);
        spannable.setSpan(new StyleSpan(Typeface.BOLD),0, nameList.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannable);
    }
    //Выделение жирным первой части текста для надевания доспехов
    private void setBoldWRText(TextView textView, String name, String wear, String remove) {
        String fullText = name +
                "\nНадеть: " + wear +
                "\nСнять: " + remove;
        SpannableString spannable = new SpannableString(fullText);
        spannable.setSpan(new StyleSpan(Typeface.BOLD),0,name.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannable);
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
